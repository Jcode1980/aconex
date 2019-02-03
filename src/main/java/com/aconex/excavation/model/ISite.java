package com.aconex.excavation.model;

public interface ISite {

    public String represenationalMap();

    public ITerrain terrainForCoordinate(Point point);

    public boolean coordinatesAreValid(Point point);

    public void excavateCoordinate(Point point);
}
