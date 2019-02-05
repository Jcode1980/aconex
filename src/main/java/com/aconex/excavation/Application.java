package com.aconex.excavation;

import java.io.File;

public class Application {
    static public void main(String args[]){
        initializeClient(args);
    }


    static private void initializeClient(String[] args){
        try{
            Client client = new Client( args.length > 0 ? args[0] : new File("src/main/resources/SiteMap.txt").getAbsolutePath());
            client.startExcavationSimluator();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


