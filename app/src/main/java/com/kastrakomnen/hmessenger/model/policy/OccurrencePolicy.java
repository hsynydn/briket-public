package com.kastrakomnen.hmessenger.model.policy;

public class OccurrencePolicy implements Policy {

    private int targetNumber;
    private int currentNumber;

    public OccurrencePolicy(int targetNumber){
        this.targetNumber = targetNumber;
        this.currentNumber = 0;
    }

    private void increment(){
        this.currentNumber++;
    }

    @Override
    public boolean check(Policy.Condition condition){
        if (condition.isFulfilled()){
            increment();
            return true;
        }
        return false;
    }

    @Override
    public boolean isFulfilled() {
        return this.targetNumber == this.currentNumber;
    }
}
