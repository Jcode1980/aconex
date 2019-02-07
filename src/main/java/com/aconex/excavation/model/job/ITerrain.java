package com.aconex.excavation.model.job;

public interface ITerrain {

    boolean hasBeenExcavated();


    Integer excavate();

    String getCode();

    TerrainType terrainType();


}
