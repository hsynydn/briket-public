package com.example.hmessenger.logic;

public class LinePattern extends Pattern{

    public LinePattern(){
        super.setPatternMap(LINE);

        super.rightDetector = LINE_RIGHT_DETECTOR;
        super.leftDetector = LINE_LEFT_DETECTOR;
        super.underDetector = LINE_UNDER_DETECTOR;

//        super.cssColor = "-fx-background-color:rgb(0, 204, 0);";
        super.color = COLOR_ORANGE;
    }

    @Override
    public short [] getRotatedMap(){
        switch(rotationCounter%2){
            case 0:
                rotationCounter++;
                rightDetector = LINE_90_RIGHT_DETECTOR;
                leftDetector = LINE_90_LEFT_DETECTOR;
                underDetector = LINE_90_UNDER_DETECTOR;
                return LINE_90;
            case 1:
                rotationCounter++;
                rightDetector = LINE_RIGHT_DETECTOR;
                leftDetector = LINE_LEFT_DETECTOR;
                underDetector = LINE_UNDER_DETECTOR;
                return LINE;
        }
        System.err.println("ERROR In Pattern -> LinePattern [No Value Returned]");
        return null;
    }

//	public String getCssColor(){
//		return this.cssColor;
//	}

}

