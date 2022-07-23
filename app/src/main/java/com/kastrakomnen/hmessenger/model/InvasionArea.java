package com.kastrakomnen.hmessenger.model;

import android.util.Pair;

import java.util.ArrayList;

public class InvasionArea {

    public ArrayList<Pair<Integer, Integer>> invaded;

    public InvasionArea(){
        invaded = new ArrayList<>();
    }

    public void add(Pair<Integer, Integer> pair){
        invaded.add(pair);
    }
}
