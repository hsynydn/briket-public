package com.kastrakomnen.hmessenger.model;

public interface Position {
    public void setX(int x);
    public void setY(int y);
    public void incrementX();
    public void incrementY();
    public void decrementX();
    public void decrementY();
    public int getX();
    public int getY();
}
