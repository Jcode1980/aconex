package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.CardinalPoint;
import com.aconex.excavation.enums.RotationDirection;

import java.awt.*;

public class Excavator implements IExcavator{
    private static final int FUEL_COST_FOR_ONE_MOVE = 1;

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



    private Integer excavateTerrain(ITerrain terrain) {
        Integer excavationCost = 0;

        if(!terrain.hasBeenExcavated()){
            excavationCost = terrain.excavate();
        }

        return excavationCost;

    }

    @Override
    public void place(Point point){
        this.point = point;
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
                    newY=newY - 1;
                    break;
                case EAST:
                    newX =newX + 1;
                    break;
                case SOUTH:
                    newY = newY + 1;
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
        this.point = nextMoveCoordinates();

        fuelUsed = fuelUsed + FUEL_COST_FOR_ONE_MOVE;

        fuelUsed = fuelUsed + excavateTerrain(terrain);

    }

    private boolean isOnSite() { return point != null;}
}
