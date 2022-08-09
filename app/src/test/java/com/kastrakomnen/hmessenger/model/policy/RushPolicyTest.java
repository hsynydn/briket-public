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


    }

    public void testRushPolicy_OnTime() throws InterruptedException {


    }

    public void testRushPolicy_NotOnTimeThenOnTime() throws InterruptedException {


    }
}