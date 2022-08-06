package com.kastrakomnen.hmessenger.model;

import com.kastrakomnen.hmessenger.model.display.DisplayData;
import com.kastrakomnen.hmessenger.model.display.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.set.SetBuilder;

import org.junit.Test;

import java.util.ArrayList;

class MockDisplayUnitController implements DisplayUnitController {

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
    public void refresh(ArrayList<ArrayList<Brick>> board) {

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
    public void removeAndRefresh(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> atPositions, ArrayList<Position> board) {

    }

    @Override
    public void refresh(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> atPositions) {

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
    public void gainScore(ArrayList<DisplayData.Score> scores) {

    }

    @Override
    public void setScore(int score) {

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
        Board board = new Board(24, 12, null, null);

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
        Board board = new Board(24, 12, new MockDisplayUnitController(), null);

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