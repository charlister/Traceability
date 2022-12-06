package main;

import processor.ThrowProcessor;
import spoon.Launcher;
import spoon.compiler.Environment;

import java.util.Scanner;

public class Application {
    private Scanner sc;

    public Application() {
        this.sc = new Scanner(System.in);
    }

    private void transformMavenProject() {
        System.out.print("Veuillez indiquer le repertoire vers le projet à analyser : ");
        String projectPath = sc.nextLine().trim();

        Launcher launcher = new Launcher();
        launcher.addInputResource(projectPath);

        Environment environment = launcher.getEnvironment();
        environment.setNoClasspath(true);
        environment.setComplianceLevel(11);
        environment.setCommentEnabled(true);
        environment.setAutoImports(true);

        launcher.addProcessor(new ThrowProcessor());

        System.err.println("Start of transformation ...");
        launcher.run();
        System.err.println("... End of transformation.");
    }

    public static void main(String[] args) {
        Application app = new Application();

        app.transformMavenProject();
    }
}
