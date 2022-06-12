package com.kastrakomnen.hmessenger.model;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BoardTest {

    @Test
    public void t1(){
        Board board = new Board(24, 12);

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
}