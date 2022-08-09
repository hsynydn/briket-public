package com.kastrakomnen.hmessenger.model;

public class WinCondition {

    private WinConditionType winConditionType;
    private long timeBound;
    private int numberOfObjective;

    public WinCondition(WinConditionType winConditionType, long timeBound, int numberOfObjective){
        this.winConditionType = winConditionType;
        this.timeBound = timeBound;
        this.numberOfObjective = numberOfObjective;
    }

    public WinConditionType getWinConditionType() {
        return winConditionType;
    }

    public long getTimeBound() {
        return timeBound;
    }

    public int getNumberOfObjective() {
        return numberOfObjective;
    }
}
