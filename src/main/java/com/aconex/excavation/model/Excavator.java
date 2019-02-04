package com.aconex.excavation.model;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

public class Excavator implements IExcavator{
    private Point point;
    private CardinalPoint cardinalPoint;
    private Integer fuelUsed = 0;


    @Override
    public void rotate(RotationDirection direction) {
        if(direction == null){throw new NullPointerException("Rotation Direction must not be null");}
        cardinalPoint = CardinalPoint.cardinalPointForRotation(cardinalPoint, direction);
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
    public void setCardinalPoint(CardinalPoint cp){
        if(cp == null){throw new NullPointerException("cardinal point must not be null");}
        this.cardinalPoint = cp;
    }

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
        if(point == null){throw new NullPointerException("point must not be null");}
        this.point = point;
        return 1;
    }
}
