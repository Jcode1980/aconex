package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

import java.awt.*;

public interface IExcavator {

    /**
     * Rotate the <code>IExcavator</> item in the direction specified in the <code>RotationDirection</code>
     * @param direction the direction (left or right)
     */
    void rotate(RotationDirection direction);

    /**
     * Retrieve the next point where the <code>IExcavationJob</code> will move to
     * based on the its Cardinal Point.
     * @return the <code>IExcavator</code>
     */
    Point nextMoveCoordinates();

    /**
     * Move the <code>IExcavationJob</code> to the point
     * based it's nextMoveCoordinates() and Excavate the terrain passed in.
     *
     * @param terrain <code>Terrain</code> to be excavated
     */
    void moveAndExcavate(ITerrain terrain);

    /**
     * Retrieve the Cardinal Point of the  <code>IExcavator</code>
     * @return the <code>CardinalPoint</code>
     */
    CardinalPoint getCardinalPoint();

    /**
     * Retrieve the fuel used by the <code>IExcavator</code>
     * @return <code>Integer</code>
     */
    Integer fuelUsed();

    /**
     * Set the location of the <code>IExcavator</code>
     * @param point the new location
     */
    void place(Point point);
}
