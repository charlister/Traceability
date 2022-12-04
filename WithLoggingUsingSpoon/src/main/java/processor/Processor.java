package processor;

import spoon.parser.SpoonParser;

public class Processor {
    public void analyse(String projectPath) {
        SpoonParser spoonParser = new SpoonParser(projectPath);

        spoonParser.addProcessor(null);

        spoonParser.run();
    }
}
