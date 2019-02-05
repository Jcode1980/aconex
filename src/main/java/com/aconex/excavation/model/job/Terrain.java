package com.aconex.excavation.model.job;

public class Terrain implements ITerrain{
    private TerrainType terrainType;
    private boolean hasBeenExcavated = false;


    Terrain(TerrainType terrainType){
        if(terrainType == null){throw new NullPointerException("Terrain type must not be null");}

        this.terrainType = terrainType;
    }

    @Override
    public void excavate() { hasBeenExcavated = true; }

    @Override
    public boolean hasBeenExcavated() {
        return hasBeenExcavated;
    }

    @Override
    public String getCode() {
        return terrainType.getCode()+"";
    }

    @Override
    public TerrainType terrainType(){ return terrainType; }

}
