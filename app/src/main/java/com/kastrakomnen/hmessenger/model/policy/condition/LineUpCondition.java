package com.kastrakomnen.hmessenger.model.policy.condition;

import com.kastrakomnen.hmessenger.model.policy.Policy;

public class LineUpCondition<What> implements Policy.Condition {

    private What what;

    public LineUpCondition(What what){
        this.what = what;
    }

    @Override
    public boolean isFulfilled() {
        return false;
    }
}
