package com.kastrakomnen.hmessenger.model;

public class Brick {

    private int id;
    private Set set;
    private Position relativePosition;
    private BrickState brickState;

    public Brick(){

    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Set getSet() {
        return set;
    }

    public void setRelativePosition(Position relativePosition) {
        this.relativePosition = relativePosition;
    }

    public Position getRelativePosition() {
        return relativePosition;
    }

    public void setBrickState(BrickState brickState) {
        this.brickState = brickState;
    }

    public BrickState getBrickState() {
        return brickState;
    }
}
