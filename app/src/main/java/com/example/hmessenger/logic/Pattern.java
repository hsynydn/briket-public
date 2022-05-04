package com.example.hmessenger.logic;

public class Pattern implements MovableObject{

    public short index = INDEX;
    public short patternMap[] = EMPTY;
    protected String cssColor = "";
    protected int rotationCounter = 0;

    protected short rightDetector [];
    protected short leftDetector [];
    protected short underDetector [];

    public short[] getPatternMap() {
        return patternMap;
    }

    public void setPatternMap(short[] patternMap) {
        this.patternMap = patternMap;
    }

    @Override
    public short getIndex_X(){
        return (short)(index >>> 8);
    }

    @Override
    public short getIndex_Y() {
        short temp = (short) (index << 8);
        return (short)(temp >>> 8);
    }

    @Override
    public short getIndex() {
        return this.index;
    }

    @Override
    public void setIndex(short index) {
        this.index = index;
    }

    @Override
    public void slideRight() {
        index += 0x0001;
    }

    @Override
    public void slideLeft() {
        index -= 0x0001;
    }

    @Override
    public void fall() {
        index += 0x0100;
    }

    @Override
    public short [] getRotatedMap() {
        // TODO Auto-generated method stub
        return null;
    }

    public short [] getRightDetector(){
        return rightDetector;
    }

    public short [] getLeftDetector(){
        return leftDetector;
    }

    public short [] getUnderDetector(){
        return underDetector;
    }

    public String getCssColor(){
        return this.cssColor;
    }
}
