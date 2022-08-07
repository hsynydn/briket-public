package com.kastrakomnen.hmessenger.model.policy;

import junit.framework.TestCase;

public class RushPolicyTest extends TestCase {

    BinaryPair<Boolean, Boolean> falseCondition = new BinaryPair<Boolean, Boolean>() {
        @Override
        public boolean getBinaryEvent(Boolean b, Boolean s) {
            return false;
        }
    };

    BinaryPair<Boolean, Boolean> trueCondition = new BinaryPair<Boolean, Boolean>() {
        @Override
        public boolean getBinaryEvent(Boolean b, Boolean s) {
            return true;
        }
    };

    public void testRushPolicy_NotOnTime() throws InterruptedException {

        Policy policy = new RushPolicy<PNull, PNull, PNull, PNull>(new PolicyListener.Ignore());
        policy.pursue(trueCondition);
        Thread.sleep(4000);

        assertEquals(policy.pursue(trueCondition), false);
    }

    public void testRushPolicy_OnTime() throws InterruptedException {

        Policy policy = new RushPolicy<PNull, PNull, PNull, PNull>(new PolicyListener.Ignore());
        policy.pursue(trueCondition);
        Thread.sleep(2000);

        assertEquals(policy.pursue(trueCondition), true);
    }

    public void testRushPolicy_NotOnTimeThenOnTime() throws InterruptedException {

        Policy policy = new RushPolicy<PNull, PNull, PNull, PNull>(new PolicyListener.Ignore());
        policy.pursue(trueCondition);
        Thread.sleep(4000);

        assertEquals(policy.pursue(trueCondition), false);

        policy.pursue(trueCondition);
        Thread.sleep(4000);

        assertEquals(policy.pursue(trueCondition), false);
    }
}