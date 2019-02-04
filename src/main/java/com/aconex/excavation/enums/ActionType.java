package com.aconex.excavation.enums;

import java.util.Arrays;
import java.util.List;


public enum ActionType{
    RIGHT("(?i)r(?-i)"),LEFT("(?i)l(?-i)"),ADVANCE("(?i)a(?-i) [0-9]+"),
    QUIT("(?i)q(?-i)");

    ActionType(String pattern){

        if(pattern == null)
            throw new NullPointerException( "Pattern must not be null");

        this.pattern = pattern;}

    private final String pattern;

    public String pattern(){return pattern;}


    public static List<ActionType> actionTypes(){
        return Arrays.asList(LEFT, RIGHT, ADVANCE, QUIT);
        //return of(PLACE, LEFT, RIGHT, MOVE, REPORT);
    }
}