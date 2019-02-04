package com.aconex.excavation.model;

import java.util.ArrayList;
import java.util.List;

public class TerrainType {
    static private List<TerrainType> terrainTypes = new ArrayList<TerrainType>();

    private String name;
    private char code;
    private Integer excavationFuelCost;




    static{
        terrainTypes.add(new TerrainType("Plain", 'o', 0));
        terrainTypes.add(new TerrainType("Rocky", 'r', 1));
        terrainTypes.add(new TerrainType("Removable Tree", 't', 1));
        terrainTypes.add(new TerrainType("Preserved Tree", 't', 1));
    }

    public TerrainType(String name, char code, Integer excavationFuelCost){
        this.name = name;
        this.code = code;
        this.excavationFuelCost = excavationFuelCost;
    }


    public String getName() {
        return name;
    }

    public char getCode() {
        return code;
    }

    static public TerrainType terrainTypeForChar(char c){
        return terrainTypes.stream().filter(terrain -> terrain.getCode() == c).findFirst().orElseThrow(() ->
                new IllegalArgumentException("terrain with code: " + c + " not found"));
    }

    public Integer getExcavationFuelCost(){return excavationFuelCost;}
}
