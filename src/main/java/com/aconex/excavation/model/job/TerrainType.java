package com.aconex.excavation.model.job;

import java.util.ArrayList;
import java.util.List;


public class TerrainType {
    static private List<TerrainType> terrainTypes = new ArrayList<>();

    private String name;
    private char code;
    private Integer excavationFuelCost;

    @SuppressWarnings("WeakerAccess")
    static public final String PLAIN = "Plain";
    @SuppressWarnings("WeakerAccess")
    static public final String ROCKY = "Rocky";
    @SuppressWarnings("WeakerAccess")
    static public final String REMOVABLE_TREE = "Removable Tree";
    @SuppressWarnings("WeakerAccess")
    static public final String PRESERVED_TREE = "Preserved Tree";

    @SuppressWarnings("WeakerAccess")
    static public final char PLAIN_CODE = 'o';
    @SuppressWarnings("WeakerAccess")
    static public final char ROCKY_CODE = 'r';
    @SuppressWarnings("WeakerAccess")
    static public final char REMOVABLE_TREE_CODE = 't';
    @SuppressWarnings("WeakerAccess")
    static public final char PRESERVED_TREE_CODE = 'T';




    public TerrainType(String name, char code, Integer excavationFuelCost){
        this.name = name;
        this.code = code;
        this.excavationFuelCost = excavationFuelCost;
    }

    @SuppressWarnings("WeakerAccess")
    public String getName() {
        return name;
    }

    @SuppressWarnings("WeakerAccess")
    public char getCode() {
        return code;
    }


    Integer getExcavationFuelCost(){return excavationFuelCost;}
}
