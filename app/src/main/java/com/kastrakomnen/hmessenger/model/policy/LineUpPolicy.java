package com.kastrakomnen.hmessenger.model.policy;

import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.set.BrickState;
import com.kastrakomnen.hmessenger.model.set.BrickType;

import java.util.ArrayList;

public class LineUpPolicy<IN1, IN2, OUT1, OUT2> implements Policy{

    private PolicyListener<OUT1, OUT2> policyListener;

    public LineUpPolicy(PolicyListener<OUT1, OUT2> policyListener){
        this.policyListener = policyListener;
    }

    @Override
    public boolean pursue(Policy policy) {
        return false;
    }
}
