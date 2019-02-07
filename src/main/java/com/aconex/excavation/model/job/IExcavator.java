package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

import java.awt.*;

public interface IExcavator {
    void rotate(RotationDirection direction);

    Point nextMoveCoordinates();

    void moveAndExcavate(ITerrain terrain);

    CardinalPoint getCardinalPoint();

    Integer fuelUsed();

    void place(Point point);
}
