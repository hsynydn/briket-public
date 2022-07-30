package com.kastrakomnen.hmessenger.view;

import android.graphics.Rect;

public class ProgressCard {

    private boolean locked;
    private int highScore;
    private int earnStar;
    private int index;

    public ProgressCard(){}

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked(){
        return locked;
    }

    public void setEarnStar(int earnStar) {
        this.earnStar = earnStar;
    }

    public int getEarnStar() {
        return earnStar;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
