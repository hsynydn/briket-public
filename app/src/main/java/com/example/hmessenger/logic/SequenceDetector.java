package com.example.hmessenger.logic;

import java.util.Stack;

public class SequenceDetector implements Variables{

    private Grid grid;
    private Stack<Integer> garbage;

    public SequenceDetector(Grid grid) {
        this.grid = grid;
        this.garbage = new Stack<Integer>();
    }

    public int detect(){

        int counter = 0;
        int detectedSequence = 0;

        for (int i = 0; i < GRID_X; i++) {
            for (int j = 0; j < GRID_Y; j++) {
                if (grid.getGridMap().get(i*GRID_Y+j).isSet()) {
                    counter++;
                }
            }

            if(counter == 12){
                garbage.add(i);
            }else{
                counter = 0;
            }
        }

        for(Integer i : garbage){
            for (int j = i*GRID_Y+11 ; j >= i*GRID_Y; j--) {
                grid.getGridMap().remove(j);
            }
        }

        for (int i = 0; i < garbage.size()*GRID_Y; i++) {
            grid.getGridMap().add(i, new Cell());
        }
        detectedSequence = garbage.size();
        garbage.removeAllElements();

        return detectedSequence;
    }
}

