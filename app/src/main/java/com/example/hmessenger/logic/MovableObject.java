package com.example.hmessenger.logic;

public interface MovableObject extends Variables {

    public short getIndex_X();

    public short getIndex_Y();

    public short getIndex();

    public void setIndex(short index);

    public void slideRight();

    public void slideLeft();

    public void fall();

    public short [] getRotatedMap();

    public short [] getRightDetector();
    public short [] getLeftDetector();
    public short [] getUnderDetector();

}

