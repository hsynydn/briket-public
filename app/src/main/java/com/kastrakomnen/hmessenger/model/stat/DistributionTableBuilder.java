package com.kastrakomnen.hmessenger.model.stat;

import java.util.ArrayList;
import java.util.Arrays;

public class DistributionTableBuilder {

    public static final ArrayList<Boolean> BOOLEAN = new ArrayList<>(Arrays.asList(true, false));

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
            case PROB_10:
                tmp.add(1);
                tmp.add(9);
                break;
            case PROB_20:
                tmp.add(2);
                tmp.add(8);
                break;
            case PROB_30:
                tmp.add(3);
                tmp.add(7);
                break;
            case PROB_40:
                tmp.add(4);
                tmp.add(6);
                break;
            case PROB_50:
                tmp.add(5);
                tmp.add(5);
                break;
            default:
                throw new IllegalArgumentException("distribution type did not recognized");
        }

        return tmp;
    }

}
