package com.kastrakomnen.hmessenger.model.display;

import com.kastrakomnen.hmessenger.model.StageStatus;
import com.kastrakomnen.hmessenger.model.set.BrickType;

public class DisplayData {

    public static class Brick{
        BrickType brickType;
    }

    public static class Board{
        public int height;
        public int width;

        public Board(int height, int width){
            this.height = height;
            this.width = width;
        }
    }

    public static class Coin{
        public int amount;
    }

    public static class Time{
        public int amount;
    }

    public static class Diamond{
        public int amount;
    }

    public static class Score{
        public int at;
        public int value;

        public Score(int at, int value){
            this.at = at;
            this.value = value;
        }
    }

    public static class DeletionAnimation{
        public int at;

        public DeletionAnimation(int at){
            this.at = at;
        }
    }

    public static class Status{
        public StageStatus stageStatus;

        public Status(StageStatus stageStatus){
            this.stageStatus = stageStatus;
        }
    }

    public enum PopUpEvent{
        COMBOx2,
        COMBOx3,
        COMBOx4,
        COMBOx5,
        STAR_LINE_UP
    }
}
