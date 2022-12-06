package localspoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.CodeFactory;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.FieldFactory;
import spoon.reflect.path.CtRole;
import spoon.reflect.reference.CtTypeReference;

import java.util.Set;
import java.util.logging.Logger;

public class ThrowProcessor extends AbstractProcessor<CtThrow> {
    @Override
    public boolean isToBeProcessed(CtThrow candidate) {
        return true;
    }

    @Override
    public void process(CtThrow ctThrow) {
        CtClass ctClass = getClass(ctThrow);

        // Attributes
        final CtTypeReference<java.util.logging.Logger> loggerTypeRef = getFactory().Code().createCtTypeReference(java.util.logging.Logger.class);
        final CtField<java.util.logging.Logger> loggerField = getFactory().Core().createField();
        loggerField.addModifier(ModifierKind.PRIVATE);
        loggerField.addModifier(ModifierKind.FINAL);
        loggerField.setType(loggerTypeRef);
        loggerField.setSimpleName("LOGGER");

        final CtTypeReference<java.util.logging.Handler> handlerTypeRef = getFactory().Code().createCtTypeReference(java.util.logging.Handler.class);
        final CtField<java.util.logging.Handler> fileHandlerField = getFactory().Core().createField();
        fileHandlerField.addModifier(ModifierKind.PRIVATE);
        fileHandlerField.addModifier(ModifierKind.FINAL);
        fileHandlerField.setType(handlerTypeRef);
        fileHandlerField.setSimpleName("fileHandler");

        ctClass.addField(loggerField);
        ctClass.addField(fileHandlerField);

        // Constructor
        Set<CtConstructor> ctConstructors = ctClass.getConstructors();
        String initLogger = "this.LOGGER = Logger.getLogger(this.getClass().getName();";
        String tryCatchFileHandler = "try {\n" +
                "            fileHandler  = new FileHandler(\"./traceability.log\");\n" +
                "            LOGGER.addHandler(fileHandler);\n" +
                "            fileHandler.setLevel(java.util.logging.Level.ALL);\n" +
                "            LOGGER.setLevel(java.util.logging.Level.ALL);\n" +
                "        } catch (java.io.IOException e) {\n" +
                "            LOGGER.log(java.util.logging.Level.INFO, \"Error occur in FileHandler.\", e);\n" +
                "        }";
        for (CtConstructor ctConstructor : ctConstructors) {
            ctConstructor.getBody().addStatement(this.getFactory().Code().createCodeSnippetStatement(initLogger));
            ctConstructor.getBody().addStatement(this.getFactory().Code().createCodeSnippetStatement(tryCatchFileHandler));
        }

        // instruction throw
        CtCodeSnippetStatement logMsgStatement = this.getFactory().Code().createCodeSnippetStatement("LOGGER.log(Level.WARNING, )");
        ctThrow.insertBefore(logMsgStatement);
        ctThrow.comment();

        // gérer la valeur retournée par la méthode
    }

    private CtClass getClass(CtThrow ctThrow) {
        CtElement ctElement = ctThrow;
        while(!(ctElement instanceof CtClass)) {
            if (ctElement == null)
                return null;
            ctElement = ctElement.getParent();
        }
        return (CtClass) ctElement;
    }

    private CtConstructor getConstructor(CtThrow ctThrow) {
        CtElement ctElement = ctThrow;
        while(!(ctElement instanceof CtConstructor)) {
            if (ctElement == null)
                return null;
            ctElement = ctElement.getParent();
        }
        return (CtConstructor) ctElement;
    }

    private CtMethod getMethod(CtThrow ctThrow) {
        CtElement ctElement = ctThrow;
        while(!(ctElement instanceof CtMethod)) {
            if (ctElement == null)
                return null;
            ctElement = ctElement.getParent();
        }
        return (CtMethod) ctElement;
    }
}
