package com.aconex.excavation;

public class Application {
    static public void main(String args[]){
        initializeClient(args[0]);
    }

    static private void initializeClient(String filePath){
        Client client = new Client(filePath);
        client.startExcavationSimluator();

    }
}


