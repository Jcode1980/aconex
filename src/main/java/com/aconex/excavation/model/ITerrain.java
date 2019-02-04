package com.aconex.excavation.model;

public interface ITerrain {

    boolean hasBeenExcavated();


    void excavate();

    String getCode();

    TerrainType terrainType();


}
