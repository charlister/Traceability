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
import java.nio.file.FileSystems;
import java.util.*;

public class Application {
    private Scanner sc;

    private StringBuilder menu;

    public Application() {
        this.sc = new Scanner(System.in);
        this.menu = new StringBuilder();
        this.fillMenu();
    }

    private void fillMenu() {
        this.menu.append("i : Insérer un système de journalisation dans le Projet WithException.\n");
        this.menu.append("a : Analyser les résultats de la journalisation depuis le projet WithLoggingManually. (/!\\le projet doit être fermé)\n");
        this.menu.append("q : Quitter l'application.\n");
    }

    private void displayMenu() {
        System.out.println("==============================  MENU  ==============================");
        System.out.println(menu.toString());
        System.out.println("====================================================================\n\n");
    }

    void run() {
        boolean quit = false;
        while (!quit) {
            this.displayMenu();
            System.err.print("choice : ");
            String choice = this.sc.nextLine().trim();
            switch (choice) {
                case "i":
                    this.transformMavenProject();
                    break;
                case "a":
                    AutoAnalysis autoAnalysis = new AutoAnalysis(".."+
                            FileSystems.getDefault().getSeparator()+
                            "WithLoggingManually"+
                            FileSystems.getDefault().getSeparator()+
                            "traceability.log");
                    System.out.println(autoAnalysis.getProfiles());
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.err.println("Invalid option !");
                    break;
            }
        }

    }

    private void transformMavenProject() {
        String projectPath = ".."+
                FileSystems.getDefault().getSeparator()+
                "WithException"+
                FileSystems.getDefault().getSeparator()+
                "src"+
                FileSystems.getDefault().getSeparator()+
                "main"+
                FileSystems.getDefault().getSeparator()+
                "java"+
                FileSystems.getDefault().getSeparator()+
                "repository";

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
        Application app = new Application();
        app.run();
        System.err.println("Byyyyyyyyyyyyyyyyyyyyyyyyeeeeeeeeeeeee !");
    }
}
