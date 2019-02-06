package com.aconex.excavation.model.job;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TerrainTypeTest {
    private TerrainType terrainType;

    @Before
    public void setUp() throws Exception {
        terrainType = new TerrainType(TerrainType.REMOVABLE_TREE, TerrainType.REMOVABLE_TREE_CODE, 1);
    }

    @Test
    public void getName() {
        assertThat(terrainType.getName(), is(TerrainType.REMOVABLE_TREE));
    }

    @Test
    public void getCode() {
        assertThat(terrainType.getCode(), is(TerrainType.REMOVABLE_TREE_CODE));
    }

    @Test
    public void terrainTypeForChar() {
        TerrainType terrainType = TerrainType.terrainTypeForChar(TerrainType.REMOVABLE_TREE_CODE);
        assertThat(terrainType.getName(), is(TerrainType.REMOVABLE_TREE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void terrainTypeForChar_shouldReturnIllegalArgumentExceptionWhenSentUnknownChar() {
        TerrainType.terrainTypeForChar('z');
    }


    @Test
    public void getExcavationFuelCost() {
        assertThat(terrainType.getExcavationFuelCost(), is(1));
    }
}