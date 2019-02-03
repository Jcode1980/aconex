package com.aconex.excavation.enums;

import java.util.Arrays;
import java.util.List;

//FIXME
//import static com.google.common.base.Preconditions.checkNotNull;
//import static java.util.List.of;

public enum ActionType{
    RIGHT("(?i)r(?-i)"),LEFT("(?i)l(?-i)"),ADVANCE("(?i)a(?-i) [0-9]+"),
    QUIT("(?i)q(?-i)");

    ActionType(String pattern){
        //FIXME
        //checkNotNull(pattern, "Pattern must not be null", pattern);
        this.pattern = pattern;}

    private final String pattern;

    public String pattern(){return pattern;}


    public static List<ActionType> actionTypes(){
        return Arrays.asList(LEFT, RIGHT, ADVANCE, QUIT);
        //return of(PLACE, LEFT, RIGHT, MOVE, REPORT);
    }
}