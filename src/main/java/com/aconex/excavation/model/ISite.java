package com.aconex.excavation.model;

import java.util.List;

public interface ISite {

    public String represenationalMap();

    public ITerrain terrainForCoordinate(Point point);

    public boolean coordinatesAreValid(Point point);

    //public Integer excavateCoordinate(Point point);

    public List<ITerrain> clearedTerrains();

    public List<ITerrain> nonClearedTerrains();

    public List<ITerrain> clearedRockyTerrains();

    public List<ITerrain> clearedProtectedTreesTerrains();

}
