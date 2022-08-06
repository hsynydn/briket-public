package com.kastrakomnen.hmessenger.model.stat;

import java.util.ArrayList;

public class DistributionTableBuilder {

    public static ArrayList<Integer> build(DistributionType distributionType, Integer partition){

        ArrayList<Integer> tmp = new ArrayList<>();

        switch (distributionType){
            case UNIFORM:
                for (int i = 0; i < partition; i++) {
                    tmp.add(1);
                }
                break;
            case NORMAL:
                throw new IllegalArgumentException("distribution normal not implemented");
            case LOG_NORMAL:
                throw new IllegalArgumentException("distribution log normal not implemented");
            default:
                throw new IllegalArgumentException("distribution type did not recognized");
        }

        return tmp;
    }

}
