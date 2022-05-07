package com.example.hmessenger.logic;

import android.util.Log;

import java.util.ArrayList;

public class CollisionDetector implements Variables{

    private static final String TAG = "CollisionDetector";

    public boolean detect(ArrayList<Cell> gridMap, Pattern pattern, Integer direction){

        short detectionArea [] = null;

        switch(direction){
            case 1:
                detectionArea = pattern.getRightDetector();
                /* Check grid right border conditions */
                for (short s : detectionArea){
                    Log.i(TAG, "detectionArea :: " + s);
                    if ( (pattern.getIndex_Y() + s) % GRID_Y == 0) return false;
                }
                break;
            case 0 :
                detectionArea = pattern.getLeftDetector();
                /* Check grid left border conditions */
                for (short s : detectionArea){
                    if (( pattern.getIndex_Y() + s + 1) % GRID_Y == 0) return false;
                }
                break;
            case 2 : detectionArea = pattern.getUnderDetector();
                break;
        }


        /**
         *   0 1
         * 0
         * 1 X X
         * 2 X X
         * 3
         *
         * BoxPattern ─
         */

        for(short i : detectionArea){
            if( (pattern.getIndex_X()*GRID_Y + pattern.getIndex_Y() + i) >= gridMap.size()){
                return false;
            }else{

                // Detect another object
                if(gridMap.get(pattern.getIndex_X()*GRID_Y + pattern.getIndex_Y() + i).isSet()){
                    return false;
                }

            }
        }

        return true;
    }

}

