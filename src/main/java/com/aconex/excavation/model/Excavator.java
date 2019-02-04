package com.aconex.excavation.model;

import com.aconex.excavation.controller.ISiteController;
import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

public class Excavator implements IExcavator{
    Point point;
    CardinalPoint cardinalPoint;
    Integer fuelUsed = 0;

    @Override
    public ISiteController excavatorController() {
        return null;
    }


    @Override
    public void rotate(RotationDirection direction) {

    }


    private int getX(){return point.getX();}

    private int getY(){return point.getY();}

    public CardinalPoint getCardinalPoint(){return cardinalPoint;}

    @Override
    public Integer fuelUsed() {
        return fuelUsed;
    }

    @Override
    public void addToFuelUsed(Integer fuelAmount) {
        fuelUsed = fuelUsed + fuelAmount;
    }

    @Override
    public Integer excavateTerrain(ITerrain terrain) {
        terrain.excavate();
        return terrain.terrainType().getExcavationFuelCost();
    }

    @Override
    public void setCardinalPoint(CardinalPoint cp){this.cardinalPoint = cp;}

    @Override
    public Point nextMoveCoordinates(){
        CardinalPoint direction = getCardinalPoint();
        int newX = getX();
        int newY = getY();

        switch(direction) {
            case NORTH:
                newY=newY+ 1;
                break;
            case EAST:
                newX =newX + 1;
                break;
            case SOUTH:
                newY = newY - 1;
                break;
            case WEST:
                newX = newX - 1;
                break;
        }
        return new Point(newX, newY);
    }

    @Override
    //this function assumes that point ONLY move 1 unit at a time
    public Integer move(Point point){
        this.point = point;
        return 1;
    }
}
