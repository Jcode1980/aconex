package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import java.awt.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExcavatorTest {
    private IExcavator excavator;

    @Mock
    private Terrain terrainMock;


    @Before
    public void setUp() {
        excavator = new Excavator(CardinalPoint.EAST);

        when(terrainMock.excavate()).thenReturn(1);

    }

    @After
    public void tearDown() {
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