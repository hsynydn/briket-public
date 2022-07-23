package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public class Set {

    private ArrayList<Formation> formations;
    private ArrayList<Brick> bricks;
    private ArrayList<ArrayList<Integer>> brickIndexers;
    private Integer currentFormationIndex;
    private Position formationOrigin;
    private SetState setState;
    private SetType setType;

    public Set(){
        formations = new ArrayList<>();
        bricks = new ArrayList<>();
        brickIndexers = new ArrayList<>();
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

    public void insert(ArrayList<Integer> brickIndexer){
        if (!formations.isEmpty()){
            if (formations.get(0).size() != brickIndexer.size()){
                throw new IllegalArgumentException("brick indexer size does not match with formation");
            }
        }

        brickIndexers.add(brickIndexer);
    }

    public void setSetType(SetType setType) {
        this.setType = setType;
    }

    public SetType getSetType() {
        return setType;
    }

    public SetState getSetState() {
        return setState;
    }

    public void setCurrentFormationIndex(Integer currentFormationIndex) {
        this.currentFormationIndex = currentFormationIndex;
    }

    public Integer getCurrentFormationIndex() {
        return currentFormationIndex;
    }

    public void setDefaultFormation(){
        currentFormationIndex = formations.size()-1;
    }

    public void setFormationOrigin(Position position){
        formationOrigin = position;
    }

    public Position getFormationOrigin(){
        return formationOrigin;
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

    public Formation getPrevFormation(){
        if (currentFormationIndex == 0){
            return formations.get(formations.size() - 1);
        }else{
            return formations.get(currentFormationIndex - 1);
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

    public Brick getBrickAt(int x, int y){

        int i = 0;
        for (Position position : formations.get(currentFormationIndex).getForm()) {
            if (formationOrigin.getX() + position.getX() == x && formationOrigin.getY() + position.getY() == y){
                return bricks.get(i);
            }
            i++;
        }

        return null;
    }

    public void setBrickAt(int x, int y, Brick brick){

        int i = 0;
        for (Position position : formations.get(currentFormationIndex).getForm()) {
            if (formationOrigin.getX() + position.getX() == x && formationOrigin.getY() + position.getY() == y){
                bricks.set(i, brick);
            }
            i++;
        }
    }

    public ArrayList<ArrayList<Integer>> getBrickIndexers() {
        return brickIndexers;
    }
}
