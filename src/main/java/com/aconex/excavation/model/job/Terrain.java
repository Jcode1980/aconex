package com.aconex.excavation.model.job;

public class Terrain implements ITerrain{
    private TerrainType terrainType;
    private boolean hasBeenExcavated = false;


    public Terrain(TerrainType terrainType){
        if(terrainType == null){throw new NullPointerException("Terrain type must not be null");}

        this.terrainType = terrainType;
    }

    @Override
    public Integer excavate() {
        hasBeenExcavated = true;
        //System.out.println("Excavating: " + terrainType.getName() +
        //        " returning excationfueld cost: " + terrainType.getExcavationFuelCost());
        return terrainType.getExcavationFuelCost();
    }

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
