package com.kastrakomnen.hmessenger.model;

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
}
