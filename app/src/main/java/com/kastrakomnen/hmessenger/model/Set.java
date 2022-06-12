package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public class Set {

    private ArrayList<Formation> formations;
    private ArrayList<Brick> bricks;
    private Integer currentFormationIndex;
    private Position basePosition;
    private SetState setState;

    public Set(){
        formations = new ArrayList<>();
        bricks = new ArrayList<>();
        setState = SetState.MOVE_STATE;
    }

    public void insert(Formation formation){

        if (!formations.isEmpty()){
            if (formations.get(0).size() != formation.size()){
                throw new IllegalArgumentException("formation sizes does not match");
            }
        }

        formations.add(formation);

        if (formation.size() == 1){
            currentFormationIndex = 0;
        }
    }

    public void setDefaultFormation(){
        currentFormationIndex = formations.size()-1;
    }

    public void setBasePosition(Position position){
        basePosition = position;
    }

    public Position getBasePosition(){
        return basePosition;
    }

    public void insert(Brick brick){

        if (!formations.isEmpty()){
            if(formations.get(0).size() == bricks.size()){
                throw new IllegalArgumentException("Number of bricks does not match with given formation");
            }
        }else{
            throw new IllegalArgumentException("Set requires formation");
        }

        bricks.add(brick);
    }

    public ArrayList<Brick> getBricks(){
        return bricks;
    }

    public ArrayList<Formation> getFormations(){
        return formations;
    }

    public Formation getCurrentFormation(){
        return formations.get(currentFormationIndex);
    }

    public Formation getNextFormation(){
        if (currentFormationIndex == formations.size() - 1){
            return formations.get(0);
        }else{
            return formations.get(currentFormationIndex + 1);
        }
    }

    public Formation cycleFormationForward(){
        if (currentFormationIndex == formations.size() - 1){
            currentFormationIndex = 0;
        }else{
            currentFormationIndex++;
        }

        return formations.get(currentFormationIndex);
    }

    public Formation cycleFormationBackward(){
        if (currentFormationIndex == 0){
            currentFormationIndex = formations.size() - 1;
        }else{
            currentFormationIndex--;
        }

        return formations.get(currentFormationIndex);
    }

    public void setSetState(SetState setState){
        this.setState = setState;
    }
}
