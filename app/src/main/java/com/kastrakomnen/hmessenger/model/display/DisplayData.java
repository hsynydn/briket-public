package com.kastrakomnen.hmessenger.model.display;

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
}
