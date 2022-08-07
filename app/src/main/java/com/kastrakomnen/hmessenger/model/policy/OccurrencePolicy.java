package com.kastrakomnen.hmessenger.model.policy;

public class OccurrencePolicy<IN1, IN2, OUT1, OUT2> implements Policy, BinaryPair<IN1, IN2> {

    private int targetNumber;
    private int currentNumber;

    public OccurrencePolicy(int targetNumber, PolicyListener<OUT1, OUT2> policyListener){
        this.targetNumber = targetNumber;
        this.currentNumber = 0;
    }

    private void increment(){
        this.currentNumber++;
    }

    @Override
    public boolean pursue(Policy policy){
        return false;
    }

    @Override
    public boolean getBinaryEvent(IN1 in1, IN2 in2) {
        return this.targetNumber == this.currentNumber;
    }
}
