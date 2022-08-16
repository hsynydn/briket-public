package com.kastrakomnen.hmessenger.model;

public class WinCondition {

    private WinConditionType winConditionType;
    private long timeBound;
    private int numberOfObjective;
    private int numberOfObjectiveCompleted;

    public WinCondition(WinConditionType winConditionType, long timeBound, int numberOfObjective){
        this.winConditionType = winConditionType;
        this.timeBound = timeBound;
        this.numberOfObjective = numberOfObjective;
        this.numberOfObjectiveCompleted = 0;
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

    public void increment(){
        numberOfObjectiveCompleted++;
    }

    public boolean isCompleted(){
        if(numberOfObjective <= numberOfObjectiveCompleted){
            return true;
        }

        return false;
    }

    public void reset(){
        numberOfObjectiveCompleted=0;
    }
}
