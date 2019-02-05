package com.aconex.excavation.enums;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CardinalPointTest {

    @Test
    public void getName_shouldReturnCorrectCardinalName() {
        assertThat(CardinalPoint.NORTH.name(),is("NORTH"));
        assertThat(CardinalPoint.WEST.name(),is("WEST"));
        assertThat(CardinalPoint.EAST.name(),is("EAST"));
        assertThat(CardinalPoint.SOUTH.name(),is("SOUTH"));
    }

    @Test
    public void rotate_shouldReturnCorrectCardinalPoints() {
        CardinalPoint cardinalPoint1 = CardinalPoint.cardinalPointForRotation(CardinalPoint.NORTH, RotationDirection.LEFT);
        assertEquals(CardinalPoint.WEST, cardinalPoint1);

        CardinalPoint cardinalPoint2 = CardinalPoint.cardinalPointForRotation(CardinalPoint.NORTH, RotationDirection.RIGHT);
        assertThat(cardinalPoint2, is(CardinalPoint.EAST));
    }

}