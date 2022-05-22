package com.kastrakomnen.hmessenger.logic;

public class LPattern extends Pattern{

    public LPattern() {
        super.setPatternMap(LSHAPE);

        super.rightDetector = LSHAPE_RIGHT_DETECTOR;
        super.leftDetector = LSHAPE_LEFT_DETECTOR;
        super.underDetector = LSHAPE_UNDER_DETECTOR;

        super.color = COLOR_GREEN;
        super.patternType = PatternType.LPattern;
    }

    @Override
    public short [] getRotatedMap(){
        switch(rotationCounter%4){
            case 0:
                rotationCounter++;
                rightDetector = LSHAPE_90_RIGHT_DETECTOR;
                leftDetector = LSHAPE_90_LEFT_DETECTOR;
                underDetector = LSHAPE_90_UNDER_DETECTOR;
                return LSHAPE_90;
            case 1:
                rotationCounter++;
                rightDetector = LSHAPE_180_RIGHT_DETECTOR;
                leftDetector = LSHAPE_180_LEFT_DETECTOR;
                underDetector = LSHAPE_180_UNDER_DETECTOR;
                return LSHAPE_180;
            case 2:
                rotationCounter++;
                rightDetector = LSHAPE_270_RIGHT_DETECTOR;
                leftDetector = LSHAPE_270_LEFT_DETECTOR;
                underDetector = LSHAPE_270_UNDER_DETECTOR;
                return LSHAPE_270;
            case 3:
                rotationCounter++;
                rightDetector = LSHAPE_RIGHT_DETECTOR;
                leftDetector = LSHAPE_LEFT_DETECTOR;
                underDetector = LSHAPE_UNDER_DETECTOR;
                return LSHAPE;
        }
        System.err.println("ERROR In Pattern : LPattern [No Value Returned]");
        return null;
    }

//	public String getCssColor(){
//		return this.cssColor;
//	}

}

