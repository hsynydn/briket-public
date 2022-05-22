package com.kastrakomnen.hmessenger.logic;

public class Pattern implements MovableObject{

    // 16 bit signed 2's complement integer
    // second byte of index (MSB) holds row number
    // first byte of index (LSB) holds column number
    public short index = INDEX;

    protected PatternType patternType;

    public short patternMap[] = EMPTY;
    protected int color;
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

    /**
     * Row Number
     * @return
     */
    @Override
    public short getIndex_X(){
        return (short)(index >>> 8);
    }

    /**
     * Column Number
     * @return
     */
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


    /* index  = 0x0006 --> 0000 0000 0000 0110
     * return = 0x0007 --> 0000 0000 0000 0111
     */
    @Override
    public void slideRight() {
        index += 0x0001;
    }

    /* index  = 0x0006 --> 0000 0000 0000 0110
     * return = 0x0005 --> 0000 0000 0000 0101
     */
    @Override
    public void slideLeft() {
        index -= 0x0001;
    }

    /* index  = 0x0006 --> 0000 0000 0000 0110
     * return = 0x0106 --> 0000 0001 0000 0110
     */
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

    public int getColor(){
        return this.color;
    }

    public PatternType getPatternType(){
        return patternType;
    }
}
