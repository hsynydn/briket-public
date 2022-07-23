package com.kastrakomnen.hmessenger.model;

import org.junit.Test;

import java.util.ArrayList;

class MockDisplayUnitController implements DisplayUnitController{

    @Override
    public void create(DisplayData.Board board) {

    }

    @Override
    public void create(DisplayData.Brick brick, Position at) {

    }

    @Override
    public void move(DisplayData.Brick brick, Position from, Position to) {

    }

    @Override
    public void move(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> fromPositions, ArrayList<Position> toPositions) {

    }

    @Override
    public void rotate(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> fromPositions, ArrayList<Position> toPositions) {

    }

    @Override
    public void remove(DisplayData.Brick brick, Position at) {

    }

    @Override
    public void startFast() {

    }

    @Override
    public void startDelay(int delay) {

    }

    @Override
    public void updateInfo() {

    }

    @Override
    public void end() {

    }

    @Override
    public void register(Subscriber subscriber) {

    }

    @Override
    public void publish() {

    }
}

public class BoardTest {

    @Test
    public void t1(){
        Board board = new Board(24, 12, null);

        board.place(SetBuilder.build(FormationType.BOX_CW0));
        System.out.println(board.toString());

        for (int i = 0; i < 7; i++) {
            board.moveRight();
        }
        System.out.println(board.toString());

        for (int i = 0; i < 24; i++) {
            board.moveDown();
        }
        System.out.println(board.toString());

        board.place(SetBuilder.build(FormationType.Z_CW0));
        System.out.println(board.toString());

        for (int i = 0; i < 3; i++) {
            board.moveRight();
        }
        System.out.println(board.toString());

        for (int i = 0; i < 24; i++) {
            board.moveDown();
        }
        System.out.println(board.toString());

        board.place(SetBuilder.build(FormationType.L_CW0));
        System.out.println(board.toString());

        for (int i = 0; i < 20; i++) {
            board.moveDown();
        }
        System.out.println(board.toString());

        board.moveRight();
        System.out.println(board.toString());
    }

    @Test
    public void testRotate(){
        Board board = new Board(24, 12, new MockDisplayUnitController());

        board.place(SetBuilder.build(FormationType.RL_CW90));
        System.out.println(board.toString());

        board.rotateCW();
        System.out.println(board.toString());
        board.rotateCW();
        System.out.println(board.toString());
        board.rotateCW();
        System.out.println(board.toString());

        System.out.println((int)Math.cos(Math.toRadians(180)));
        System.out.println((int)Math.sin(Math.toRadians(180)));

    }
}