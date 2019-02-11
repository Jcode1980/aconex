package com.aconex.excavation.model.job;

import com.aconex.excavation.service.job.ExcavationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SiteTest {
    private static final String TEST_MAPS_FILE = "src/main/resources/test/TestMap.txt";
    private ISite site;
    @Mock
    private Point pointMock;
    private TerrainType rockyTerrainType;

    @Before
    public void setUp() throws Exception {
        ExcavationService excavationService = new ExcavationService();
        site = new Site(excavationService.createTerrainsMap(TEST_MAPS_FILE));
        rockyTerrainType = excavationService.terrainTypeForChar('r');
        when(pointMock.getX()).thenReturn(1d);
        when(pointMock.getY()).thenReturn(2d);
    }

    @After
    public void tearDown(){
        site = null;
    }

    @Test
    public void represenationalMap() {
        String expectedMap =
                "o o t o o o o o o o \n" +
                "o o o o o o o T o o \n" +
                "r r r o o o o T o o \n" +
                "r r r r o o o o o o \n" +
                "r r r r r t o o o o ";

        assertThat(site.represenationalMap(), is(expectedMap));
    }

    @Test
    public void terrainForCoordinate() {
        ITerrain terrain = site.terrainForCoordinate(pointMock);
        assertThat(terrain.terrainType(), is(rockyTerrainType));
    }

    @Test
    public void coordinatesAreValid() {
        assertTrue(site.coordinatesAreValid(new Point(9,4)));

        assertFalse(site.coordinatesAreValid(new Point(11,5)));
        assertFalse(site.coordinatesAreValid(new Point(10,6)));
        assertFalse(site.coordinatesAreValid(new Point(-1,0)));
        assertFalse(site.coordinatesAreValid(new Point(0,-1)));
    }

    @Test(expected = NullPointerException.class)
    public void coordinatesAreValid_shouldThrowExceptionWhenPassedNullValue() {
        site.coordinatesAreValid(null);
    }


    @Test
    public void clearedTerrains() {
        List<ITerrain> clearedTerrains = site.clearedTerrains();
        assertThat(clearedTerrains, hasSize(0));
    }

    @Test
    public void nonClearedTerrains(){
        List<ITerrain> nonClearTerrains = site.nonClearedTerrains();
        assertThat(nonClearTerrains, hasSize(50));
    }

    @Test
    public void clearedRockyTerrains() {
        List<ITerrain> clearedRockyTerrains = site.clearedRockyTerrains();
        assertThat(clearedRockyTerrains, hasSize(0));
    }


    @Test
    public void clearedProtectedTreesTerrains() {
        List<ITerrain> clearedProtectedTreesTerrains = site.clearedProtectedTreesTerrains();
        assertThat(clearedProtectedTreesTerrains, hasSize(0));
    }
}