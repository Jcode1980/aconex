package com.aconex.excavation.controller;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

import java.util.Optional;

public interface ISiteController {
    /**
     * The next move coordinates is based off the move function of the
     * <code>PlaceableItem</code>. This method checks the position of
     * the next move specified by the <code>PlaceableItem</code>, and if
     * it's a valid position, it will set the x, y values. The Cardinal
     * Point direction of the <code>PlaceableItem</code> item stays the same.
     *
     * @return <code>boolean</> to specify if the move was valid and
     * <code>PlaceableItem</code>'s coordinates were updated.
     */
    boolean move();

    /**
     * This methods rotates the <code>PlaceableItem</code> on the board
     * in the direction specified by the <code>RotationDirection</code>
     * parameter.
     * @param direction the direction in which the <code>PlaceableItem</code>
     *                  will be rotated.
     */
    void rotate(RotationDirection direction);

    /**
     * Checks first if the coordinates (x,y). Valid coordinates are values
     * non negative integers and are within the <code>GridBoards</> size.
     *
     * @param x the x value of the coordinate being passed in
     * @param y the y value of the coordinate being passed in
     * @param cp the direction at which the item should be facing
     * @return <code>boolean</code> true if <code>PlaceableItem</code> was placed
     *          successfully on board and false if not placed successfully.
     */
    boolean place(int x, int y, CardinalPoint cp);

    /**
     *
     * @return <code>String</code> which are the details (x, y, Cardinal Point) of
     * the <code>PlaceableItem</code> on the board.
     */
    Optional<String> report();
}
