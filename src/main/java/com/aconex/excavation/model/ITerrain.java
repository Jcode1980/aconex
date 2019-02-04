package com.aconex.excavation.model;

public interface ITerrain {

    boolean hasBeenExcavated();

    String getName();

    void excavate();

    String getCode();

    TerrainType terrainType();


}
