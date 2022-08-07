package com.kastrakomnen.hmessenger.model.policy;

import com.kastrakomnen.hmessenger.model.set.Brick;

import java.util.ArrayList;

public class PolicyChecker {

    private ArrayList<Policy> policies;

    public PolicyChecker(){
        this.policies = new ArrayList<>();
    }

    public void addPolicy(Policy policy){
        this.policies.add(policy);
    }

    public void scanPolicies(){
    }

}
