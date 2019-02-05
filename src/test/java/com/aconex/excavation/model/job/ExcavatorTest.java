package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.awt.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExcavatorTest {
    private IExcavator excavator;

    @Mock
    private Terrain terrainMock;

    @Mock
    private TerrainType terrainTypeMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        excavator = new Excavator(CardinalPoint.EAST);

        when(terrainTypeMock.getExcavationFuelCost()).thenReturn(1);
        when(terrainMock.terrainType()).thenReturn(terrainTypeMock);

    }

    @After
    public void tearDown() throws Exception {
        excavator = null;
    }

    @Test
    public void rotate() {
        excavator.rotate(RotationDirection.LEFT);
        assertThat(excavator.getCardinalPoint(),is(CardinalPoint.NORTH));
        excavator.rotate(RotationDirection.RIGHT);
        assertThat(excavator.getCardinalPoint(),is(CardinalPoint.EAST));
    }

    @Test
    public void getCardinalPoint() {
        assertThat(excavator.getCardinalPoint(), is(CardinalPoint.EAST));
    }

    @Test
    public void fuelUsed() {
        excavator.moveAndExcavate(terrainMock);
        assertThat(excavator.fuelUsed(), is(2));
    }

    @Test
    public void nextMoveCoordinates() {
        Point point = excavator.nextMoveCoordinates();
        assertThat(point, is(new Point(0,0)));
    }


    @Test
    public void moveAndExcavate() {
        excavator.moveAndExcavate(terrainMock);
        verify(terrainMock).excavate();
    }
}