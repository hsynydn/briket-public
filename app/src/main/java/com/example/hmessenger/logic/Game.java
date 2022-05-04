package com.example.hmessenger.logic;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

//import javafx.application.Platform;
//
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {

    protected Grid grid;
    protected RandomGenerator randomGenerator;
    protected SequenceDetector sequenceDetector;
    protected CollisionDetector collisionDetector;
    protected DisplayUnitController controller;
    protected Pattern activePattern;
    protected Timer timer;

    private Music tetris_gameboy_play;
    private Music tetris_gameboy_end;

    public Game(DisplayUnitController controller)
            throws
//            UnsupportedAudioFileException,
            IOException
//            LineUnavailableException
    {

        this.grid = new Grid();
        this.randomGenerator = new RandomGenerator();
        this.sequenceDetector  = new SequenceDetector(this.grid);
        this.collisionDetector = new CollisionDetector();
        this.controller = controller;
        this.activePattern = null;
        this.timer = new Timer();
        this.tetris_gameboy_play = new Music("tetris_gameboy_play.wav");
        this.tetris_gameboy_end = new Music("tetris_gameboy_end.wav");
    }

    public void start(){

        tetris_gameboy_play.start();

        timer.schedule(new TimerTask(){

            @Override
            public void run() {

                if(grid.getGridMap().get(6).isSet()){
                    timer.cancel();
//                    controller.gameOver.setVisible(true);
                    tetris_gameboy_play.stop();
                    tetris_gameboy_end.start();
                }

                if(activePattern==null){
                    activePattern = (Pattern)randomGenerator.generate();
                    grid.importMovableObject(activePattern);
                }



                for(Integer i : controller.getStack()){
                    if(i == Variables.RIGHT_KEY){
                        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.RIGHT_SIDE)){
                            grid.scrollMovableToRight();
                        }
                    }else if(i == Variables.LEFT_KEY){
                        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.LEFT_SIDE)){
                            grid.scrollMovableToLeft();
                        }
                    }else if(i == Variables.ROTATE_KEY){
                        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.RIGHT_SIDE)){
                            grid.rotate();
                        }
                    }else if(i == Variables.DOWN_KEY){
                        while(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
                            grid.fall();
                            int sequence = sequenceDetector.detect();
                            if(sequence != 0){
                                controller.setScore(sequence);
                            }
                            //controller.scoreBoard.setText("00000001");
                        }
                        controller.refreshMonitor(grid.getGridMap());
                    }
                }

                if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
                    grid.fall();
                    int sequence = sequenceDetector.detect();
                    if(sequence != 0){
                        controller.setScore(sequence);
                    }
                    controller.refreshMonitor(grid.getGridMap());
                }else{
                    System.out.println("Active Pattern Null");
                    activePattern = null;
                }

                controller.emptyActionStack();

            }
        }, 0, 250);

    }

    public void exit(){
        tetris_gameboy_play.stop();
        timer.cancel();
//        Platform.exit();
    }
}
