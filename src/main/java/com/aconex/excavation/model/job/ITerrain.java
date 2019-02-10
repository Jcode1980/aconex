package com.aconex.excavation.model.job;

public interface ITerrain {

    /**
     *Returns <code>booelean</code> to specify if the <code>ITerrain</code>
     * has been excavated.
     *@return <code>boolean</code>
     */
    boolean hasBeenExcavated();

    /**
     *Function that exavates the Terrian
     * @return <code>Integer</code> Fueld used to excavate Terrain
     */
    Integer excavate();

    /**
     * Retrieve the code of the terrain.
     * @return <code>String</code> the code
     */
    String getCode();

    /**
     * Retrieve the Terrain Type of the Terrain
     * @return <code>TerrainType</code> the TerrainType.
     */
    TerrainType terrainType();


}
