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


    static{
        terrainTypes.add(new TerrainType(PLAIN, PLAIN_CODE, 0));
        terrainTypes.add(new TerrainType(ROCKY, ROCKY_CODE, 1));
        terrainTypes.add(new TerrainType(REMOVABLE_TREE, REMOVABLE_TREE_CODE, 1));
        terrainTypes.add(new TerrainType(PRESERVED_TREE, PRESERVED_TREE_CODE, 1));
    }

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

    public static TerrainType terrainTypeForChar(char c){
        return terrainTypes.stream().filter(terrain -> terrain.getCode() == c).findFirst().orElseThrow(() ->
                new IllegalArgumentException("terrain with code: " + c + " not found"));
    }

    Integer getExcavationFuelCost(){return excavationFuelCost;}
}
