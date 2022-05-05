package com.example.hmessenger.logic;

public class BoxPattern extends Pattern{

    public BoxPattern() {
        super.setPatternMap(BOX);

        super.rightDetector = BOX_RIGHT_DETECTOR;
        super.leftDetector = BOX_LEFT_DETECTOR;
        super.underDetector = BOX_UNDER_DETECTOR;

        super.color = COLOR_BLUE;
    }

    @Override
    public short [] getRotatedMap(){
        return BOX;
    }
}

