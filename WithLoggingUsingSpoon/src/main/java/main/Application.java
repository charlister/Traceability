package main;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import processor.ReturnProcessor;
import processor.ThrowProcessor;
import profiling.analysis.AutoAnalysis;
import spoon.Launcher;
import spoon.compiler.Environment;

import java.io.*;
import java.util.*;

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
        launcher.addProcessor(new ReturnProcessor());

        System.err.println("Start of transformation ...");
        launcher.run();
        System.err.println("... End of transformation.");
    }

    public static void main(String[] args) {
        AutoAnalysis autoAnalysis = new AutoAnalysis("C:\\Users\\senio\\IdeaProjects\\ERL\\A_Rendre\\Traceability\\WithLoggingManually\\traceability.log");
        System.out.println(autoAnalysis.getProfiles());

//        Application app = new Application();
//        app.transformMavenProject();
    }
}
