package com.aconex.excavation;

import com.aconex.excavation.model.IExcavationJob;
import com.aconex.excavation.service.ExcavationService;
import com.aconex.excavation.service.IExcavationService;

import java.util.Scanner;

public class Client {
    private IExcavationService excavationService;
    private IExcavationJob excavationJob;

    public Client(String filePath) throws Exception{
        excavationService = ExcavationService.excavationService();
        excavationJob  = excavationService.createExcavationJob(filePath);
    }

    public void startExcavationSimluator(){
        //do something here
        excavationService.startExcavationJob(excavationJob);
        System.out.println("the cardinal point is: " + excavationJob.excavator().getCardinalPoint());
        wellcomPhase();

    }

    private void wellcomPhase(){
        System.out.println("Welcome to the Aconex site clearing simulator. This is a map of\n" +
                "the site:\n\n");
        System.out.println(excavationJob.site().represenationalMap());
        System.out.println("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East.:\n\n");

        System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        String commandStr;

        while (!(commandStr = scanner.nextLine()).equals("q")) {
            boolean commandWasRun = excavationService.processCommandForJob(commandStr, excavationJob);
            System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
        }
    }

    private void endPhase(){
        System.out.println("The simulation has ended at your request. These are the commands\n"+
            "you issued:");
        System.out.println(excavationJob.instructions());
        System.out.println("The costs for this land clearing operation were:");
    }

}
