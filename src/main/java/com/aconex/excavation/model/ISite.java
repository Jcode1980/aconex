package com.aconex.excavation.model;

import java.util.List;

public interface ISite {

    String represenationalMap();

    ITerrain terrainForCoordinate(Point point);

    boolean coordinatesAreValid(Point point);

    List<ITerrain> clearedTerrains();

    List<ITerrain> nonClearedTerrains();

    List<ITerrain> clearedRockyTerrains();

    List<ITerrain> clearedProtectedTreesTerrains();

}
