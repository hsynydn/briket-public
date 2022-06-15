package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public class Board {

    private final int height;
    private final int width;

    private Set[][] boardMap;
    private Set activeSet;
    private ArrayList<Set> setList;

    private ArrayList<ArrayList<Brick>> board;

    public Board(int height, int width){

        boardMap = new Set[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardMap[i][j] = null;
            }
        }

        activeSet = null;
        setList = new ArrayList<>();

        board = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ArrayList<Brick> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            board.add(row);
        }

        this.height = height;
        this.width = width;
    }

    public boolean place(Set set){

        Position basePosition = new RelativePosition(5,2);

        if (activeSet!=null){
            return false;
        }

        /* Board has no space left */
        if (board.get(basePosition.getY()).get(basePosition.getX()) != null)
            return false;

        /* Check does all positions in formation suit */
        Formation formation = set.getCurrentFormation();
        for (Position p : formation.getForm() ) {
            if (board
                    .get(p.getY() + basePosition.getY())
                    .get(p.getX() + basePosition.getX()) != null){
                return false;
            }
        }

        /* Place the set into the board
         * then let the set know its base position */
        for (Brick brick : set.getBricks()) {
            Position relPos = brick.getRelativePosition();
            board.get(relPos.getY() + basePosition.getY()).set(relPos.getX() + basePosition.getX(), brick);
        }
        set.setFormationOrigin(new RelativePosition(5,2));

        activeSet = set;

        return true;
    }

    public boolean moveRight(){

        if (activeSet==null) return false;

        /* Clean currently occupied space */
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), null);
        }

        /* Check move space is suitable */
        boolean failFlag = false;
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            if (o.getX() + p.getX() + 1 >= width){
                failFlag = true;
                break;
            }

            if (board.get(o.getY() + p.getY()).get(o.getX() + p.getX() + 1) != null){
                failFlag = true;
            }
        }

        /* Update base index */
        if (!failFlag){
            activeSet.getFormationOrigin().incrementX();
        }

        /* Restore previous occupied region */
        /* OR */
        /* Mark newly occupied region */
        for (Brick brick : activeSet.getBricks()){
            if (brick.getBrickState() == BrickState.DEAD) continue;

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), brick);
        }

        return true;
    }

    public boolean moveLeft(){

        if (activeSet==null) return false;

        /* Clean currently occupied space */
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), null);
        }

        /* Check move space is suitable */
        boolean failFlag = false;
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            if (o.getX() + p.getX() - 1 < 0) failFlag = true;

            if (board.get(o.getY() + p.getY()).get(o.getX() + p.getX() - 1) != null){
                failFlag = true;
            }
        }

        /* Update base index */
        if (!failFlag){
            activeSet.getFormationOrigin().decrementX();
        }

        /* Restore previous occupied region */
        /* OR */
        /* Mark newly occupied region */
        for (Brick brick : activeSet.getBricks()){
            if (brick.getBrickState() == BrickState.DEAD) continue;

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), brick);
        }

        return true;
    }

    public boolean moveDown(){

        if (activeSet==null) return false;

        /* Clean currently occupied space */
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), null);
        }

        /* Check move space is suitable */
        boolean failFlag = false;
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            if (o.getY() + p.getY() + 1 >= height){
                failFlag = true;
                break;
            }

            if (board.get(o.getY() + p.getY() + 1).get(o.getX() + p.getX()) != null){
                failFlag = true;
            }
        }

        /* Update base index */
        if (!failFlag){
            activeSet.getFormationOrigin().incrementY();
        }

        /* Restore previous occupied region */
        /* OR */
        /* Mark newly occupied region */
        for (Brick brick : activeSet.getBricks()){
            if (brick.getBrickState() == BrickState.DEAD) continue;

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), brick);
        }

        if (failFlag){
            activeSet = null;
            return false;
        }

        return true;
    }

    public boolean rotateCW(){

        if (activeSet==null) return false;

        /* Clean currently occupied space */
        for (Brick brick : activeSet.getBricks()) {

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), null);
        }

        /* Check move space is suitable */
        Formation nextFormation = activeSet.getNextFormation();
        boolean failFlag = false;
        for (Position p : nextFormation.getForm()) {

            Position o = activeSet.getFormationOrigin();

            if (o.getY() + p.getY() < 0 || o.getY() + p.getY() >= height)
                failFlag=true;

            if (o.getX() + p.getX() < 0 || o.getX() + p.getX() >= width)
                failFlag=true;

            if (board.get(o.getY() + p.getY()).get(o.getX() + p.getX()) != null){
                failFlag = true;
            }
        }

        if (!failFlag){
            /* Update base index */
            activeSet.cycleFormationForward();
            int i = 0;
            for (Brick brick : activeSet.getBricks()) {
                brick.setRelativePosition(activeSet.getCurrentFormation().getForm().get(i));
                i++;
            }
        }

        /* Restore previous occupied region */
        /* OR */
        /* Mark newly occupied space */
        for (Brick brick : activeSet.getBricks()) {
            if (brick.getBrickState() == BrickState.DEAD) continue;

            Position o = activeSet.getFormationOrigin();
            Position p = brick.getRelativePosition();

            board.get(o.getY() + p.getY()).set(o.getX() + p.getX(), brick);
        }

        return true;
    }

    public Set getActiveSet(){
        return activeSet;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board.get(i).get(j) == null){
                    stringBuilder.append("─");
                }else{
//                    if (board.get(i).get(j).getBrickAt(j, i) == null){
//                        stringBuilder.append("─");
//                    }else{
                        stringBuilder.append("X");
//                    }
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
