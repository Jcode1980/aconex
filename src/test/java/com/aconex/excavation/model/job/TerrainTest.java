package com.aconex.excavation.model.job;

import com.aconex.excavation.service.job.ExcavationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TerrainTest {
    private ExcavationService excavationService;
    private Terrain terrain;
    private TerrainType terrainType;

    @Before
    public void setUp() throws Exception {
        excavationService = new ExcavationService();
        terrainType = excavationService.terrainTypeForChar(TerrainType.REMOVABLE_TREE_CODE);
        terrain = new Terrain(terrainType);
    }

    @After
    public void tearDown() throws Exception {
        terrain = null;
    }

    @Test
    public void excavate() {
        terrain.excavate();
        assertTrue(terrain.hasBeenExcavated());
    }

    @Test
    public void hasBeenExcavated() {
        assertFalse(terrain.hasBeenExcavated());
    }

    @Test
    public void getCode() {
        assertThat(terrain.getCode(), is(TerrainType.REMOVABLE_TREE_CODE+""));
    }

    @Test
    public void terrainType() {
        assertThat(terrain.terrainType(), is(terrainType));
    }
}