package com.example.hmessenger.logic;

import java.util.Stack;

public class SequenceDetector implements Variables{

    private Grid grid;

    // Row index in grid, which will be removed
    private Stack<Integer> garbage;

    public SequenceDetector(Grid grid) {
        this.grid = grid;
        this.garbage = new Stack<Integer>();
    }

    /**
     * ─  0 1 2 3 4 5 6 7 8 9 10 11
     * 0  x x
     * 1  x
     * 2  x
     * 3  x
     * 4  x
     * 5  x
     * 6  x
     * 7  x
     * 8  x
     * 9  x
     * 10 x
     * 11 x
     *
     * @return
     */

    public int detect(){

        int counter = 0;
        int detectedSequence = 0;

        for (int i = 0; i < GRID_X; i++) {
            for (int j = 0; j < GRID_Y; j++) {
                if (grid.getGridMap().get(i*GRID_Y+j).isSet()) {
                    counter++;
                }
            }

            // if counter is 12, then whole row is SET
            if(counter == 12){
                // This row will be added to items to remove
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

