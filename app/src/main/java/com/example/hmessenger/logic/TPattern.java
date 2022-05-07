package com.example.hmessenger.logic;

public class TPattern extends Pattern{

    public TPattern(){
        super.setPatternMap(TSHAPE);

        super.rightDetector = TSHAPE_RIGHT_DETECTOR;
        super.leftDetector = TSHAPE_LEFT_DETECTOR;
        super.underDetector = TSHAPE_UNDER_DETECTOR;

        super.color = COLOR_RED;
        super.patternType = PatternType.TPattern;
    }

    @Override
    public short [] getRotatedMap(){
        switch(rotationCounter%4){
            case 0:
                rotationCounter++;
                rightDetector = TSHAPE_90_RIGHT_DETECTOR;
                leftDetector = TSHAPE_90_LEFT_DETECTOR;
                underDetector = TSHAPE_90_UNDER_DETECTOR;
                return TSHAPE_90;
            case 1:
                rotationCounter++;
                rightDetector = TSHAPE_180_RIGHT_DETECTOR;
                leftDetector = TSHAPE_180_LEFT_DETECTOR;
                underDetector = TSHAPE_180_UNDER_DETECTOR;
                return TSHAPE_180;
            case 2:
                rotationCounter++;
                rightDetector = TSHAPE_270_RIGHT_DETECTOR;
                leftDetector = TSHAPE_270_LEFT_DETECTOR;
                underDetector = TSHAPE_270_UNDER_DETECTOR;
                return TSHAPE_270;
            case 3:
                rotationCounter++;
                rightDetector = TSHAPE_RIGHT_DETECTOR;
                leftDetector = TSHAPE_LEFT_DETECTOR;
                underDetector = TSHAPE_UNDER_DETECTOR;
                return TSHAPE;
        }
        return null;
    }

//	public String getCssColor(){
//		return this.cssColor;
//	}

}

