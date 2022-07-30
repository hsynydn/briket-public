package com.kastrakomnen.hmessenger.model;

import android.util.Log;

import java.util.ArrayList;

public class Board {

    private static final String TAG = "Board";

    private final int height;
    private final int width;
    private final int invisibleHeight = 4;

    private int score;

    private Set activeSet;

    private final ArrayList<ArrayList<Brick>> board;
    private final ArrayList<ArrayList<Brick>> visibleBoard;
    private final DisplayUnitController displayUnitController;

    public Board(int height, int width, DisplayUnitController displayUnitController){

        this.height = height + invisibleHeight;
        this.width = width;
        this.score = 0;
        this.displayUnitController = displayUnitController;
        this.visibleBoard = new ArrayList<>();

        activeSet = null;

        board = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            ArrayList<Brick> row = new ArrayList<>();
            for (int j = 0; j < this.width; j++) {
                row.add(null);
            }
            board.add(row);
        }
    }

    public boolean place(Set set){

        Log.i(TAG, "Board place called");

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

        for (Brick brick : set.getBricks()) {
            displayUnitController.create(
                    null,
                    new RelativePosition(
                            activeSet.getFormationOrigin().getX() + brick.getRelativePosition().getX(),
                            activeSet.getFormationOrigin().getY() + brick.getRelativePosition().getY()
                    )
            );
        }

        Log.i(TAG, "Board place end");

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
                break;
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

        if (!failFlag){
            this.updateVisibleBoard();
            displayUnitController.refresh(visibleBoard);
        }

        return !failFlag;
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

            if (o.getX() + p.getX() - 1 < 0) {
                failFlag = true;
                break;
            }

            if (board.get(o.getY() + p.getY()).get(o.getX() + p.getX() - 1) != null){
                failFlag = true;
                break;
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

        if (!failFlag){
            this.updateVisibleBoard();
            displayUnitController.refresh(visibleBoard);
        }

        return !failFlag;
    }

    public boolean moveDown(){

        Log.i(TAG, "Board moveDown called");

        if (activeSet==null) {
            Log.d(TAG, "activeSet=null");
            return false;
        }

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
            ArrayList<Integer> indices = checkSequence();

            /* Check is there any row to delete */
            if (!indices.isEmpty()){

                ArrayList<Position> visualUpdateAtArrayList = new ArrayList<>();
                ArrayList<DisplayData.Score> scores = new ArrayList<>();
                for (Integer i: indices) {
                    for (int j = 0; j < width; j++) {
                        Position o = board.get(i).get(j).getSet().getFormationOrigin();
                        Position p = board.get(i).get(j).getRelativePosition();

                        board.get(i).get(j).setBrickState(BrickState.DEAD);

                        visualUpdateAtArrayList.add(new RelativePosition(o.getX() + p.getX(), o.getY() + p.getY()));
                    }

                    board.remove((int)i);
                    scores.add(new DisplayData.Score(i - invisibleHeight, 100));

                    ArrayList<Brick> newRow = new ArrayList<>();
                    for (int j = 0; j < width; j++) {
                        newRow.add(null);
                    }
                    board.add(0, newRow);
                }

                score += 100 * scores.size();
                Log.d(TAG, "Score is " + score);

                this.updateVisibleBoard();

                displayUnitController.refresh(visibleBoard);
                displayUnitController.gainScore(scores);
                displayUnitController.setScore(score);
                activeSet = null;
                return true;
            }

            activeSet = null;
            return false;
        }

        this.updateVisibleBoard();
        displayUnitController.refresh(visibleBoard);

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

            if (o.getY() + p.getY() < 0 || o.getY() + p.getY() >= height){
                failFlag=true;
                break;
            }

            if (o.getX() + p.getX() < 0 || o.getX() + p.getX() >= width){
                failFlag=true;
                break;
            }

            if (board.get(o.getY() + p.getY()).get(o.getX() + p.getX()) != null){
                failFlag = true;
                break;
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

        if (failFlag){
            return false;
        }

        this.updateVisibleBoard();
        displayUnitController.refresh(visibleBoard);

        return true;
    }

    /**
     * Find sequences in board
     * @return
     */
    private ArrayList<Integer> checkSequence(){

        Log.v(TAG, "{checkSequence}");
        ArrayList<Integer> indices = new ArrayList<>();

        int k = 0;
        for (ArrayList<Brick> row : board) {
            int i = 0;
            for (Brick brick : row) {
                Log.v(TAG, "{checkSequence} loop");
                if (brick == null) continue;

                if (brick.getBrickState() == BrickState.LIVE){
                    i++;
                }

                if (i == width){
                    indices.add(k);
                    Log.d(TAG, "{checkSequence} ── Delete Row::" + k);
                }
            }
            k++;
        }

        return indices;
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

    public Set getActiveSet(){
        return activeSet;
    }

    public int getScore() {
        return score;
    }

    private void updateVisibleBoard(){
        visibleBoard.clear();
        for (int i = invisibleHeight; i < board.size(); i++) {
            visibleBoard.add(board.get(i));
        }
    }
}

