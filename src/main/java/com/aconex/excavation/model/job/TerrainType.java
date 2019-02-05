package com.aconex.excavation.model.job;

import java.util.ArrayList;
import java.util.List;


class TerrainType {
    static private List<TerrainType> terrainTypes = new ArrayList<>();

    private String name;
    private char code;
    private Integer excavationFuelCost;

    @SuppressWarnings("WeakerAccess")
    static public String PLAIN = "Plain";

    @SuppressWarnings("WeakerAccess")
    static public String ROCKY = "Rocky";

    @SuppressWarnings("WeakerAccess")
    static public String REMOVABLE_TREE = "Removable Tree";

    @SuppressWarnings("WeakerAccess")
    static public String PRESERVED_TREE = "Preserved Tree";


    static{
        terrainTypes.add(new TerrainType(PLAIN, 'o', 0));
        terrainTypes.add(new TerrainType(ROCKY, 'r', 1));
        terrainTypes.add(new TerrainType(REMOVABLE_TREE, 't', 1));
        terrainTypes.add(new TerrainType(PRESERVED_TREE, 'T', 1));
    }

    private TerrainType(String name, char code, Integer excavationFuelCost){
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

    static TerrainType terrainTypeForChar(char c){
        return terrainTypes.stream().filter(terrain -> terrain.getCode() == c).findFirst().orElseThrow(() ->
                new IllegalArgumentException("terrain with code: " + c + " not found"));
    }

    Integer getExcavationFuelCost(){return excavationFuelCost;}
}
