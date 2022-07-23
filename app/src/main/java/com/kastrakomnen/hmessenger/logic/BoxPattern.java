package com.kastrakomnen.hmessenger.logic;

public class BoxPattern extends Pattern{

    public BoxPattern() {
        super.setPatternMap(BOX);

        super.rightDetector = BOX_RIGHT_DETECTOR;
        super.leftDetector = BOX_LEFT_DETECTOR;
        super.underDetector = BOX_UNDER_DETECTOR;

        super.color = COLOR_BLUE;
        super.patternType = PatternType.BoxPattern;
    }

    @Override
    public short [] getRotatedMap(){
        return BOX;
    }
}

