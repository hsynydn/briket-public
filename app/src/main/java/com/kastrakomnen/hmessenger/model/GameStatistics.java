package com.kastrakomnen.hmessenger.model;

public class GameStatistics {

    private int score;

    private int numberOfSequence;

    private int numberOfDoubleCombo;
    private int numberOfTripleCombo;
    private int numberOfQuadrupleCombo;

    private int timeDuration;

    private int collectedCoin;
    private int collectedDiamond;

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setNumberOfSequence(int numberOfSequence) {
        this.numberOfSequence = numberOfSequence;
    }

    public int getNumberOfSequence() {
        return numberOfSequence;
    }

    public void setNumberOfDoubleCombo(int numberOfDoubleCombo) {
        this.numberOfDoubleCombo = numberOfDoubleCombo;
    }

    public int getNumberOfDoubleCombo() {
        return numberOfDoubleCombo;
    }

    public void setNumberOfTripleCombo(int numberOfTripleCombo) {
        this.numberOfTripleCombo = numberOfTripleCombo;
    }

    public int getNumberOfTripleCombo() {
        return numberOfTripleCombo;
    }

    public void setNumberOfQuadrupleCombo(int numberOfQuadrupleCombo) {
        this.numberOfQuadrupleCombo = numberOfQuadrupleCombo;
    }

    public int getNumberOfQuadrupleCombo() {
        return numberOfQuadrupleCombo;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setCollectedCoin(int collectedCoin) {
        this.collectedCoin = collectedCoin;
    }

    public int getCollectedCoin() {
        return collectedCoin;
    }

    public void setCollectedDiamond(int collectedDiamond) {
        this.collectedDiamond = collectedDiamond;
    }

    public int getCollectedDiamond() {
        return collectedDiamond;
    }
}
