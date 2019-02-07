package com.aconex.excavation.model.job;

import java.awt.*;
import java.util.List;

public interface ISite {

    String represenationalMap();

    ITerrain terrainForCoordinate(Point point);

    boolean coordinatesAreValid(Point point);

    List<ITerrain> nonClearedTerrains();

    List<ITerrain> clearedTerrains();

    List<ITerrain> clearedRockyTerrains();

    List<ITerrain> clearedProtectedTreesTerrains();

    Integer getHeight();

    Integer getWidth();

}
