package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

import java.awt.*;

public class Excavator implements IExcavator{
    private Point point;
    private CardinalPoint cardinalPoint;
    private Integer fuelUsed = 0;

    @Override
    public void rotate(RotationDirection direction) {
        if(direction == null){throw new NullPointerException("Rotation Direction must not be null");}
        cardinalPoint = CardinalPoint.cardinalPointForRotation(cardinalPoint, direction);
    }

    public Excavator(CardinalPoint cp){this.cardinalPoint = cp;}

    private int getX(){return (int)point.getX();}

    private int getY(){return (int)point.getY();}

    public CardinalPoint getCardinalPoint(){return cardinalPoint;}

    @Override
    public Integer fuelUsed() {
        return fuelUsed;
    }

    @Override
    public void addToFuelUsed(Integer fuelAmount) {
        fuelUsed = fuelUsed + fuelAmount;
    }


    private Integer excavateTerrain(ITerrain terrain) {
        //if(terrain == null){throw new NullPointerException("terrain must not be null");}

        terrain.excavate();
        return terrain.terrainType().getExcavationFuelCost();
    }

    @Override
    public Point nextMoveCoordinates(){
        Point newPoint;

        if(!isOnSite()){
            newPoint = new Point(0,0);
        }
        else{
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
            newPoint = new Point(newX, newY);
        }

        return newPoint;
    }

    @Override
    //this function assumes that point ONLY move 1 unit at a time
    public void moveAndExcavate(ITerrain terrain){
        //if(point == null){throw new NullPointerException("point must not be null");}
        fuelUsed = fuelUsed + 1;

        this.point = nextMoveCoordinates();

        if(!terrain.hasBeenExcavated()){
            fuelUsed = fuelUsed + excavateTerrain(terrain);
        }
    }

    private boolean isOnSite() { return point != null;}
}
