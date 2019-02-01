package com.aconex.excavation;

import com.aconex.excavation.model.IExcavationJob;
import com.aconex.excavation.service.ExcavationService;
import com.aconex.excavation.service.IExcavationService;

import java.util.Scanner;

public class Client {
    private IExcavationService excavationService;
    private IExcavationJob excavationJob;

    public Client(String filePath){
        excavationService = ExcavationService.excavationService();

        IExcavationJob excavationJob  = excavationService.createExcavationJob(filePath);
    }

    public void startExcavationSimluator(){
        //do something here
        excavationService.startExcavationJob(excavationJob);
        wellcomPhase();

    }

    private void wellcomPhase(){
        System.out.println("Welcome to the Aconex site clearing simulator. This is a map of\n" +
                "the site:\n\n");
        excavationJob.site().represenationalMap();
        System.out.println("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East.:\n\n");

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);


        while (!scanner.nextLine().equals("quit")) {
            String commandStr = scanner.nextLine();
            excavationService.processCommandForJob(commandStr, excavationJob);
        }
    }

}
