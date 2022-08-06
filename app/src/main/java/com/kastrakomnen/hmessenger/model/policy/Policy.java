package com.kastrakomnen.hmessenger.model.policy;

public interface Policy {
    boolean check(Condition condition);
    boolean isFulfilled();

    interface Condition {
        boolean isFulfilled();
    }
}
