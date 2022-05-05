package com.example.hmessenger.logic;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.hmessenger.R;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Game {

    private static final String TAG = "Game";

    ScheduledExecutorService service;
    ScheduledFuture scheduledFuture;

    protected Grid grid;
    protected RandomGenerator randomGenerator;
    protected SequenceDetector sequenceDetector;
    protected CollisionDetector collisionDetector;
    protected DisplayUnitController displayUnitController;
    protected Pattern activePattern;
    protected Timer timer;

    private Handler.Callback handler;

    private Music tetris_gameboy_play;
    private Music tetris_gameboy_end;

    public Game(Context context, DisplayUnitController displayUnitController, Handler.Callback handler)
            throws
            IOException
    {

        this.grid = new Grid();
        this.randomGenerator = new RandomGenerator();
        this.sequenceDetector  = new SequenceDetector(this.grid);
        this.collisionDetector = new CollisionDetector();
        this.displayUnitController = displayUnitController;
        this.activePattern = null;
        this.timer = new Timer();
        this.handler = handler;
        this.tetris_gameboy_play = new Music(context, R.raw.tetris_gameboy_play);
        this.tetris_gameboy_end = new Music(context, R.raw.tetris_gameboy_end);

        service = Executors.newScheduledThreadPool(1);
    }

    public void start(){

        tetris_gameboy_play.start();

        scheduledFuture = service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "Periodic Check");
                        schedCheck();
                    }
                },
                1000,
                300,
                TimeUnit.MILLISECONDS);
    }

    private void schedCheck(){

        Message m = new Message();
        m.obj = grid.getGridMap();

        if(grid.getGridMap().get(6).isSet()){
            service.shutdown();
            tetris_gameboy_play.stop();
            tetris_gameboy_end.start();
            displayUnitController.visibleGameOver();
        }

        if(activePattern==null){
            Log.i(TAG, "Active Pattern Null");
            Log.i(TAG, "Generated a Pattern");
            activePattern = (Pattern)randomGenerator.generate();
            grid.importMovableObject(activePattern);
        }

        Log.i(TAG, "displayUnitController.getStack().size() ── " + displayUnitController.getStack().size());

        for(Event i : displayUnitController.getStack()){
            if(i == Event.RIGHT_MOVE){
                if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.RIGHT_SIDE)){
                    grid.scrollMovableToRight();
                }
            }else if(i == Event.LEFT_MOVE){
                if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.LEFT_SIDE)){
                    grid.scrollMovableToLeft();
                }
            }else if(i == Event.ROTATE_RIGHT){
                if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.RIGHT_SIDE)){
                    grid.rotate();
                }
            }else if(i == Event.FREE_FALL){
                while(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
                    grid.fall();
                }
//                int sequence = sequenceDetector.detect();
//                if(sequence != 0){
//                    displayUnitController.setScore(sequence);
//                }
                handler.handleMessage(m);
//                displayUnitController.refreshMonitor(grid.getGridMap());
            }
        }

        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
            Log.i(TAG, "Grid will fall one step");
            grid.fall();
            Log.i(TAG, "Check is it Sequence");
//            int sequence = sequenceDetector.detect();
//            if(sequence != 0){
//                displayUnitController.setScore(sequence);
//            }
            Log.i(TAG, "Refresh Monitor");
            handler.handleMessage(m);
//            displayUnitController.refreshMonitor(grid.getGridMap());
        }else{
            System.out.println("Active Pattern Null");
            activePattern = null;
        }

        displayUnitController.emptyActionStack();
    }

    public void exit(){
        tetris_gameboy_play.stop();
        timer.cancel();
    }
}
