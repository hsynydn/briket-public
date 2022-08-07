package com.kastrakomnen.hmessenger.model.policy;

public interface Policy {
    /**
     * Long term contract of the policy
     * You provide binaryPairs to the policy
     * if eligible according to the policy,
     * then you have been followed the policy
     * @param policy
     * @return
     */
    boolean pursue(Policy policy);
}
