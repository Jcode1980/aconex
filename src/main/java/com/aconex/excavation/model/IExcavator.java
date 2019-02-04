package com.aconex.excavation.model;

import com.aconex.excavation.controller.ISiteController;
import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

public interface IExcavator {
    ISiteController excavatorController();


    void rotate(RotationDirection direction);

    Point nextMoveCoordinates();

    Integer move(Point point);

    void setCardinalPoint(CardinalPoint cp);

    CardinalPoint getCardinalPoint();

    Integer excavateTerrain(ITerrain terrain);

    Integer fuelUsed();

    void addToFuelUsed(Integer fuelAmount);

}
