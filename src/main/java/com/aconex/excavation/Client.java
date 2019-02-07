package com.aconex.excavation;

import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.model.invoice.IInvoice;
import com.aconex.excavation.service.job.ExcavationService;
import com.aconex.excavation.service.invoice.FinanceService;
import com.aconex.excavation.service.job.IExcavationService;
import com.aconex.excavation.service.invoice.IFinanceService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Client {
    private IExcavationService excavationService;
    private IExcavationJob excavationJob;
    private IFinanceService financeService;

    private InputStream commandsStream;

    Client(String filePath, InputStream commandsStream) throws IOException {

        excavationService = ExcavationService.excavationService();
        financeService = FinanceService.financeService();
        excavationJob  = excavationService.createExcavationJob(filePath);
        this.commandsStream = commandsStream;
    }

    public void startExcavationSimluator(){
        //do something here
        //excavationService.startExcavationJob(excavationJob);
        System.out.println("the cardinal point is: " + excavationJob.excavator().getCardinalPoint());
        wellcomPhase();

    }

    private void wellcomPhase(){
        System.out.println("Welcome to the Aconex site clearing simulator. This is a map of\n" +
                "the site:\n");
        System.out.println(excavationJob.site().represenationalMap());
        System.out.println("The bulldozer is currently located at the Northern edge of the\n" +
                "site, immediately to the West of the site, and facing East.:\n\n");

        // create a scanner so we can read the command-line input
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(commandsStream);
        String commandStr;

        boolean continueProgram = true;
        while (continueProgram) {
            System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
            commandStr = scanner.nextLine();
            continueProgram = excavationService.processCommandForJob(commandStr, excavationJob);
        }

        endPhase();
    }

    private void endPhase(){
        System.out.println("The simulation has ended at your request. These are the commands\n"+
            "you issued:");
        System.out.println(excavationJob.instructions());
        System.out.println("The costs for this land clearing operation were:");

        IInvoice invoice = financeService.invoiceForJob(excavationJob);

        System.out.println(invoice.costsDisplayString());
    }

    public IExcavationJob job(){
        return excavationJob;
    }

}
