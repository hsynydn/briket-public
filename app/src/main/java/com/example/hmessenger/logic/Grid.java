package com.example.hmessenger.logic;

import java.util.ArrayList;

public class Grid{

    private final int GRID_SIZE = Variables.GRID_Y*Variables.GRID_X;

    private ArrayList<Cell> board;
    private MovableObject movableObject;
    private short [] patternMap;
    private int index;

    public Grid(){
        System.out.println("Loading");

        board = new ArrayList<Cell>();
        for (int i=0; board.size()<GRID_SIZE; i++) {
            board.add(i, new Cell());
        }
        int counter=1;
        for(Cell c : board){
            if(c.isSet()){
                counter++;
            }
        }
        System.out.println("[Constructor GRID] counter " + counter);
    }

    public ArrayList<Cell> getGridMap(){
        return board;
    }

    public void importMovableObject(MovableObject movableObject){
        this.movableObject = movableObject;
        patternMap = ((Pattern)movableObject).getPatternMap();
        updateIndex();
        placePattern();
    }

    public void scrollMovableToRight(){
        movableObject.slideRight();
        cleanPatternRemain();
        updateIndex();
        placePattern();
    }

    public void scrollMovableToLeft(){
        movableObject.slideLeft();
        cleanPatternRemain();
        updateIndex();
        placePattern();
    }

    public void fall(){
        movableObject.fall();
        cleanPatternRemain();
        updateIndex();
        placePattern();
    }

    public void rotate(){
        cleanPatternRemain();
        patternMap = movableObject.getRotatedMap();
        ((Pattern)this.movableObject).rightDetector = movableObject.getRightDetector()  ;
        ((Pattern)this.movableObject).leftDetector  = movableObject.getLeftDetector()   ;
        ((Pattern)this.movableObject).underDetector = movableObject.getUnderDetector()  ;
        placePattern();
    }

    private void cleanPatternRemain(){
        for(short pm : patternMap){
            board.get(index + pm).reset();
        }
    }

    private void placePattern(){
        for(short pm : patternMap){
            board.get(index + pm).set();
            board.get(index + pm).setColor(((Pattern)movableObject).getColor());
            //System.out.println(((Pattern)movableObject).getCssColor());
        }
    }

    public boolean controlPatternArea(){
        if(board.get(Variables.INDEX).isSet()){
            return true;
        }
        return false;
    }

    private void updateIndex(){
        index = (int)((movableObject.getIndex_X()*Variables.GRID_Y) + movableObject.getIndex_Y());
    }
}

