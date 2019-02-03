package com.aconex.excavation.model;

public class Terrain implements ITerrain{
    private TerrainType terrainType;
    private boolean hasBeenExcavated = false;

    public Terrain(TerrainType terrainType){
        this.terrainType = terrainType;
    }

    @Override
    public void excavateTerrain() {

    }

    @Override
    public boolean hasBeenExcavated() {
        return hasBeenExcavated;
    }

    @Override
    public String getName() {
        return terrainType.getName();
    }

    @Override
    public String getCode() {
        return terrainType.getCode()+"";
    }
}
