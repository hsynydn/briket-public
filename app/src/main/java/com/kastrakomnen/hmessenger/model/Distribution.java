package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Distribution<T> {

    private Map<Integer, T> distributionMap;
    private Random random;

    protected ArrayList<Integer> weights;
    protected ArrayList<T> elements;

    public Distribution(ArrayList<Integer> values, ArrayList<T> elements){
        this.weights = values;
        this.elements = elements;

        onInit();
    }

    public Distribution(DistributionType distributionType, ArrayList<T> elements){
        this.weights = DistributionTableBuilder.build(distributionType, elements.size());
        this.elements = elements;

        onInit();
    }

    private void onInit(){
        distributionMap = new HashMap<>();
        ArrayList<Integer> normWeights = new ArrayList<>();
        random = new Random();

        int totalWeight = 0;

        for (Integer weight : weights) {
            totalWeight += weight;
        }

        double normalizeRatio = 100.0 / totalWeight;

        for (Integer weight : weights) {
            normWeights.add((int) (weight * normalizeRatio * 10));
        }

        int previousIndex = 0;
        int counter = 0;
        for (Integer norm : normWeights) {
            for (int i = previousIndex; i < previousIndex + norm; i++) {
                distributionMap.put(i, elements.get(counter));
            }

            counter++;
            previousIndex += norm;
        }
    }

    public T next(){
        return distributionMap.get(random.nextInt(distributionMap.size()));
    }

    public Map<Integer, T> getDistributionMap(){
        return distributionMap;
    }
}
