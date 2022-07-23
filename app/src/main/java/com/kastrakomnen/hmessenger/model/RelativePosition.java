package com.kastrakomnen.hmessenger.model;

public class RelativePosition implements Position{

    private int x;
    private int y;

    public RelativePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void incrementX() {
        x++;
    }

    @Override
    public void incrementY() {
        y++;
    }

    @Override
    public void decrementX() {
        x--;
    }

    @Override
    public void decrementY() {
        y--;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
