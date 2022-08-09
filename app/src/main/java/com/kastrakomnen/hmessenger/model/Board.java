package com.kastrakomnen.hmessenger.model;

import android.util.Log;

import com.kastrakomnen.hmessenger.model.display.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.policy.PolicyChecker;
import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.set.BrickState;
import com.kastrakomnen.hmessenger.model.set.BrickType;
import com.kastrakomnen.hmessenger.model.set.Formation;
import com.kastrakomnen.hmessenger.model.set.Set;
import com.kastrakomnen.hmessenger.model.stat.GameStatCollector;

import java.util.ArrayList;

import kotlin.Triple;

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
    private final GameStatCollector gameStatCollector;
    private final PolicyChecker policyChecker;

    public Board(
            int height,
            int width,
            DisplayUnitController displayUnitController,
            GameStatCollector gameStatCollector,
            PolicyChecker policyChecker
    ){

        this.height = height + invisibleHeight;
        this.width = width;
        this.score = 0;
        this.displayUnitController = displayUnitController;
        this.gameStatCollector = gameStatCollector;
        this.policyChecker = policyChecker;
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

            ArrayList<Triple<BoardEvent, Integer, ArrayList<Position>>> list2Delete = new ArrayList<>();

            boolean isContinue = false;

            for (int i = 0; i < height; i++) {

                int nofLive = 0;
                int nofNorm = 0;
                int nofStar = 0;
                ArrayList<Position> listOfStarPositions = new ArrayList<>();

                for (int j = 0; j < width; j++) {

                    if(board.get(i).get(j) == null){
                        isContinue = true;
                        break;
                    }

                    if (board.get(i).get(j).getBrickState() == BrickState.LIVE){
                        nofLive++;
                        if (board.get(i).get(j).getBrickType() == BrickType.NORMAL){
                            nofNorm++;
                            listOfStarPositions.add(new RelativePosition(j, i));
                        }else if (board.get(i).get(j).getBrickType() == BrickType.STAR){
                            nofStar++;
                        }
                    }else{
                        isContinue = true;
                        break;
                    }
                }

                if (isContinue){
                    isContinue = false;
                    continue;
                }

                if (nofNorm == width){
                    list2Delete.add(new Triple<>(BoardEvent.NORM_LINEUP, i, null));
                }else if (nofStar == width){
                    list2Delete.add(new Triple<>(BoardEvent.STAR_LINEUP, i, null));
                }else{
                    list2Delete.add(new Triple<>(BoardEvent.NORM_WITH_STAR_LINEUP, i, listOfStarPositions));
                }
            }

            for (Triple<BoardEvent, Integer, ArrayList<Position>> triple :  list2Delete) {

                ArrayList<Brick> newRow;

                switch (triple.component1()){
                    case NORM_LINEUP:
                        Log.d(TAG, "NORM_LINEUP");
                        for (int i = 0; i < width; i++) {
                            board.get(triple.component2()).get(i).setBrickState(BrickState.DEAD);
                        }
                        board.remove((int)triple.component2());

                        newRow = new ArrayList<>();
                        for (int j = 0; j < width; j++) {
                            newRow.add(null);
                        }
                        board.add(0, newRow);
                        break;
                    case STAR_LINEUP:
                        Log.d(TAG, "STAR_LINEUP");
                        for (int i = 0; i < width; i++) {
                            board.get(triple.component2()).get(i).setBrickState(BrickState.DEAD);
                        }
                        board.remove((int)triple.component2());

                        newRow = new ArrayList<>();
                        for (int j = 0; j < width; j++) {
                            newRow.add(null);
                        }
                        board.add(0, newRow);
                        break;
                    case NORM_WITH_STAR_LINEUP:
                        Log.i(TAG, "NORM_WITH_STAR_LINEUP");

                        for (Position position : triple.component3()) {
                            board.get(position.getY()).get(position.getX()).setBrickState(BrickState.DEAD);
                            for (int i = position.getY(); i > 0; i--) {
                                board.get(i).set(position.getX(), board.get(i-1).get(position.getX()));
                            }
                        }

                        break;
                }
            }

            gameStatCollector.setCombo(list2Delete.size());
            gameStatCollector.setScore(0);
            gameStatCollector.setTime(0);

/*            // Repeat 10 lines up // Occurrence Policy + LineUp Policy
            // Repeat 10 Lines Up + 1 Star Line // Occurrence Policy + LineUp Policy (Normal + Star)

            Policy lineUpPolicy = new LineUpPolicy<BrickType, ArrayList<Brick>, Integer, PNull>(new PolicyListener<Integer, PNull>() {
                @Override
                public void onListen(Integer integer, PNull pNull) {
                    // Index of the lineup
                    // I need to delete that line
                }
            });

            Policy rushPolicy = new RushPolicy<>(new PolicyListener<PNull, PNull>() {
                @Override
                public void onListen(PNull pNull, PNull pNull2) {

                }
            });

            Policy occurrencePolicy = new OccurrencePolicy<Board, PNull, Boolean, Brick>(10, new PolicyListener<Boolean, Brick>() {
                    @Override
                    public void onListen(Boolean aBoolean, Brick brick) {
                        // What happen when occurrence policy realized
                    }
            });

            rushPolicy.pursue(lineUpPolicy);
            occurrencePolicy.pursue(rushPolicy);*/

            this.updateVisibleBoard();

            displayUnitController.refresh(visibleBoard);

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

