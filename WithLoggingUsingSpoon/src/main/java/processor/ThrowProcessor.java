package processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;

import java.util.Set;

public class ThrowProcessor extends AbstractProcessor<CtThrow> {
    @Override
    public boolean isToBeProcessed(CtThrow candidate) {
        return getClass(candidate).getSimpleName().equals("ProductRepository");
    }

    @Override
    public void process(CtThrow ctThrow) {
        CtClass ctClass = getClass(ctThrow);

        if (ctClass.getField("LOGGER") == null && ctClass.getField("fileHandler") == null) {
            // Attributes
            final CtTypeReference<java.util.logging.Logger> loggerTypeRef = getFactory().Code().createCtTypeReference(java.util.logging.Logger.class);
            final CtField<java.util.logging.Logger> loggerField = getFactory().Core().createField();
            loggerField.addModifier(ModifierKind.PRIVATE);
            loggerField.addModifier(ModifierKind.STATIC);
            loggerField.addModifier(ModifierKind.FINAL);
            loggerField.setType(loggerTypeRef);
            loggerField.setSimpleName("LOGGER");

            final CtTypeReference<java.util.logging.Handler> handlerTypeRef = getFactory().Code().createCtTypeReference(java.util.logging.Handler.class);
            final CtField<java.util.logging.Handler> fileHandlerField = getFactory().Core().createField();
            fileHandlerField.addModifier(ModifierKind.PRIVATE);
            fileHandlerField.addModifier(ModifierKind.FINAL);
            fileHandlerField.setType(handlerTypeRef);
            fileHandlerField.setSimpleName("fileHandler");

            ctClass.addFieldAtTop(fileHandlerField);
            ctClass.addFieldAtTop(loggerField);

            // Constructor
            Set<CtConstructor> ctConstructors = ctClass.getConstructors();
            String initLogger = String.format("this.LOGGER = java.util.logging.Logger.getLogger(%s.class.getName());", ctClass.getSimpleName());
            String tryCatchFileHandler = "try {\n" +
                    "   fileHandler  = new java.util.logging.FileHandler(\"./traceability.log\");\n" +
                    "   LOGGER.addHandler(fileHandler);\n" +
                    "   fileHandler.setLevel(java.util.logging.Level.ALL);\n" +
                    "   LOGGER.setLevel(java.util.logging.Level.ALL);\n" +
                    "} catch (java.io.IOException e) {\n" +
                    "   LOGGER.log(java.util.logging.Level.SEVERE, \"Error occur in FileHandler.\", e);\n" +
                    "}";
            for (CtConstructor ctConstructor : ctConstructors) {
                ctConstructor.getBody().addStatement(this.getFactory().Code().createCodeSnippetStatement(initLogger));
                ctConstructor.getBody().addStatement(this.getFactory().Code().createCodeSnippetStatement(tryCatchFileHandler));
            }
        }

        // instruction throw
        CtCodeSnippetStatement logMsgStatement = this.getFactory().Code().createCodeSnippetStatement("LOGGER.log(Level.WARNING, String.format(\"{\\\"user_id\\\":%d,\\\"msg\\\":%s}\", userId, msg))");
        ctThrow.insertBefore(logMsgStatement);
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
}
