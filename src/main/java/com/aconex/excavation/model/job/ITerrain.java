package com.aconex.excavation.model.job;

public interface ITerrain {

    boolean hasBeenExcavated();


    void excavate();

    String getCode();

    TerrainType terrainType();


}
