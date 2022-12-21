package processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtReturn;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

public class ReturnProcessor extends AbstractProcessor<CtReturn> {
    @Override
    public boolean isToBeProcessed(CtReturn candidate) {
        return getClass(candidate).getSimpleName().equals("ProductRepository");
    }

    @Override
    public void process(CtReturn ctReturn) {
        if (this.getMethod(ctReturn).getSimpleName().equals("getProductById")) {
            CtCodeSnippetStatement logMsgStatement = this.getFactory().Code().createCodeSnippetStatement("LOGGER.log(Level.INFO, String.format(\"{\\\"user_id\\\":%d,\\\"product_id\\\":%d,\\\"product_price\\\":%s}\", userId, productId, productWrapper.get().getPrice()))");
            ctReturn.insertBefore(logMsgStatement);
        }
        else {
            CtCodeSnippetStatement logMsgStatement = this.getFactory().Code().createCodeSnippetStatement("LOGGER.log(Level.INFO, String.format(\"{\\\"user_id\\\":%d}\", userId))");
            ctReturn.insertBefore(logMsgStatement);
        }
    }

    private CtClass getClass(CtReturn ctReturn) {
        CtElement ctElement = ctReturn;
        while(!(ctElement instanceof CtClass)) {
            if (ctElement == null)
                return null;
            ctElement = ctElement.getParent();
        }
        return (CtClass) ctElement;
    }

    private CtMethod getMethod(CtReturn ctReturn) {
        CtElement ctElement = ctReturn;
        while(!(ctElement instanceof CtMethod)) {
            if (ctElement == null)
                return null;
            ctElement = ctElement.getParent();
        }
        return (CtMethod) ctElement;
    }
}
