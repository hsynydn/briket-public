package com.kastrakomnen.hmessenger.model.set;

import com.kastrakomnen.hmessenger.model.stat.DistributionType;

public class Agent {

    private BrickType brickType;
    private DistributionType distributionType;

    public Agent(BrickType brickType, DistributionType distributionType){
        this.brickType = brickType;
        this.distributionType = distributionType;
    }

    public BrickType getBrickType() {
        return brickType;
    }

    public DistributionType getDistributionType() {
        return distributionType;
    }
}
