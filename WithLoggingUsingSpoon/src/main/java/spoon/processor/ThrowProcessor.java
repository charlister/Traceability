package spoon.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtThrow;

public class ThrowProcessor extends AbstractProcessor<CtThrow> {
    @Override
    public boolean isToBeProcessed(CtThrow candidate) {
        return true;
    }

    @Override
    public void process(CtThrow ctThrow) {

    }
}
