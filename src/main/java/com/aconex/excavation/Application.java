package com.aconex.excavation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

public class Application {
    public static final String DEMO_SITE_MAP = "src/main/resources/SiteMap.txt";

    static public void main(String args[]){
        initializeClient(args);
    }


    static private void initializeClient(String[] args){
        try{
            //InputStream in = getResourceAsStream(DEMO_SITE_MAP);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String mapsFile = args.length > 0 ? args[0] : Paths.get(DEMO_SITE_MAP).toString();
            System.out.println("using maps file: " + mapsFile);

            InputStream instructionsStream = args.length > 1 ? new FileInputStream(new File(args[1])) : System.in;

            Client client = new Client(mapsFile , instructionsStream);
            client.startExcavationSimluator();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


