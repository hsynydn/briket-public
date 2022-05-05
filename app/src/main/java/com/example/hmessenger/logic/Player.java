package com.example.hmessenger.logic;

import java.io.IOException;

//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {

    private Game game;
    private int score;

    public Player(DisplayUnitController displayUnitController)
            throws
//            UnsupportedAudioFileException,
            IOException
//            LineUnavailableException
    {
//        this.game = new Game(displayUnitController);
    }

    public String getScore(){
        return score+"";
    }

    public void setScore(int score){
        this.score = this.score + score;
    }

    public void startGame(){
        game.start();

    }

    public void exitGame(){
        game.exit();
    }
}
