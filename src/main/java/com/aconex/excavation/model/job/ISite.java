package com.aconex.excavation.model.job;

import java.awt.*;
import java.util.List;

public interface ISite {

    /**
     *Retrieve the String representation of the map
     *@return <code>String</code> Represenation of the site map
     */
    String represenationalMap();

    /**
     *Retrieve the <code>ITerrain</code> for a <code>Point</code>
     *@param point the point
     *@return <code>ITerrain</code>
     */
    ITerrain terrainForCoordinate(Point point);

    /**
     * Checks weather a point is valid for the <code>Site</code>
     * @param point
     * @return <code>boolean</code>
     */
    boolean coordinatesAreValid(Point point);

    /**
     * Retrieve a list of all non cleared terrians
     * for the site.
     *
     * @return <code>List<ITerrain></></code> non cleared Terrains
     */
    List<ITerrain> nonClearedTerrains();

    /**
     * Retrieve a list of all cleared terrians
     * for the site.
     *
     * @return <code>List<ITerrain></></code> Cleared Terrains
     */
    List<ITerrain> clearedTerrains();


    /**
     * Retrieve a list of all cleared rocky terrians
     * for the site.
     *
     * @return <code>List<ITerrain></></code> Rocky Terrains
     */
    List<ITerrain> clearedRockyTerrains();

    /**
     * Retrieve a list of all cleared protected trees terrians
     * for the site.
     *
     * @return <code>List<ITerrain></></code> cleared protected trees Terrains
     */
    List<ITerrain> clearedProtectedTreesTerrains();


    /**
     * Retrieve the height of the <code>Site</code>
     * @return <code>Integer</code> the height.
     */
    Integer getHeight();

    /**
     * Retrieve the width of the <code>Site</code>
     * @return <code>Integer</code> the width.
     */
    Integer getWidth();

}
