package com.aconex.excavation.model;

public class Point {
    private int x;
    private int y;


    public Point(int x , int y){
        if(x >= 0){throw new IllegalArgumentException("x value must be greater than 0");}
        if(y >= 0){throw new IllegalArgumentException("y value must be greater than 0");}

        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public String toString(){ return "x: " + getX() + " y: " + getY();}
}
