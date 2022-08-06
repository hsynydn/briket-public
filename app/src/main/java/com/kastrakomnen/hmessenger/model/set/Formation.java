package com.kastrakomnen.hmessenger.model.set;

import com.kastrakomnen.hmessenger.model.Position;

import java.util.ArrayList;

public class Formation {

    private ArrayList<Position> form;

    public Formation(){
        form = new ArrayList<>();
    }

    public Formation(ArrayList<Position> form){
        this.form = form;
    }

    public void insert(Position position){
        form.add(position);
    }

    public int size(){
        return form.size();
    }

    public ArrayList<Position> getForm(){
        return form;
    }
}
