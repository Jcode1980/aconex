package com.aconex.excavation.model;

import java.util.ArrayList;
import java.util.List;

public class TerrainType {
    static private List<TerrainType> terrainTypes = new ArrayList<TerrainType>();

    private String name;
    private char code;


    static{
        terrainTypes.add(new TerrainType("Rocks", 'r'));
        terrainTypes.add(new TerrainType("Tree", 't'));
        terrainTypes.add(new TerrainType("Reserved Tree", 'R'));
    }

    public TerrainType(String name, char code){
        this.name = name;
        this.code = code;
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
}
