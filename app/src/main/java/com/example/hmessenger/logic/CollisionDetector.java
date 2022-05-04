package com.example.hmessenger.logic;

import java.util.ArrayList;

public class CollisionDetector implements Variables{

    public boolean detect(ArrayList<Cell> gridMap, Pattern pattern, Integer direction){

        short detectionArea [] = null;

        switch(direction){
            case 1: detectionArea = pattern.getRightDetector();
                break;
            case 0 : detectionArea = pattern.getLeftDetector();
                break;
            case 2 : detectionArea = pattern.getUnderDetector();
                break;
        }

        for(short i : detectionArea){
            if( (pattern.getIndex_X()*GRID_Y + pattern.getIndex_Y() + i) >= gridMap.size()){
                return false;
            }else{
                if(gridMap.get(pattern.getIndex_X()*GRID_Y + pattern.getIndex_Y() + i).isSet()){
                    return false;
                }
            }
        }

        return true;
    }

}

