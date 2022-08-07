package com.kastrakomnen.hmessenger.model.policy;

public class RushPolicy<IN1, IN2, OUT1, OUT2> implements Policy{

    private boolean isFulfilled;
    private long initialTime;
    private final int timePeriod;

    private PolicyListener<OUT1, OUT2> policyListener;

    public RushPolicy(PolicyListener<OUT1, OUT2> policyListener){
        this.isFulfilled = false;
        this.initialTime = 0;
        this.timePeriod = 3;

        this.policyListener = policyListener;
    }

    @Override
    public boolean pursue(Policy policy) {
        return false;
    }
}
