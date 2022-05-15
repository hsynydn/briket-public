package com.example.hmessenger.logic;

import com.example.hmessenger.R;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Game {

    private static final String TAG = "Game";
    private static Semaphore animation_mutex = new Semaphore(1);

    ScheduledExecutorService service;
    ScheduledFuture scheduledFuture;
    private static int sch_counter = 0;

    protected Grid grid;
    protected RandomGenerator randomGenerator;
    protected SequenceDetector sequenceDetector;
    protected CollisionDetector collisionDetector;
    protected DisplayUnitController displayUnitController;
    protected Pattern activePattern;
    protected Pattern nextPattern;
    protected Timer timer;

    private android.os.Handler handler;

    private Music tetris_gameboy_play;
    private Music tetris_gameboy_end;
    private Music audio_fx_line_destroy;

    private GameState gameState;

    public Game(Context context, DisplayUnitController displayUnitController, Handler handler)
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
        this.audio_fx_line_destroy = new Music(context, R.raw.fx_audio_distortions );

        gameState = GameState.NOT_STARTED;

        service = Executors.newScheduledThreadPool(1);
    }

    public void start(){

        Log.i(TAG, "Game.start()");

        nextPattern = (Pattern)randomGenerator.generate();
        gameState   = GameState.START;

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

    public void pause(){
        gameState = GameState.PAUSE;
        scheduledFuture.cancel(true);
    }

    public void resume(){
        gameState = GameState.RESUME;
        start();
    }

    public void end(){
        gameState = GameState.END;
    }

    private void schedCheck(){

        // If this place is set, it means there is no room to create new object
        // End the game
        if(grid.getGridMap().get(6).isSet()){
            service.shutdown();
//            tetris_gameboy_play.stop();
//            tetris_gameboy_end.start();
            displayUnitController.visibleGameOver();
        }

        // Create new active pattern
        if(activePattern==null){
            Log.i(TAG, "Check is it Sequence");

            try {
                animation_mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Integer> detected_row_indices = sequenceDetector.detect();

            if (!detected_row_indices.isEmpty()){
                audio_fx_line_destroy.start();
                displayUnitController.burnFx(detected_row_indices);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            animation_mutex.release();

            Log.i(TAG, "Active Pattern Null");
            Log.i(TAG, "Generated a Pattern");
            activePattern = nextPattern;
            nextPattern = (Pattern)randomGenerator.generate();
            grid.importMovableObject(activePattern);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    displayUnitController.setNextPattern(nextPattern.getPatternType());
                }
            });
        }

        try {
            animation_mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
            Log.i(TAG, "Periodic pattern fall");
            grid.fall();
            displayUnitController.refreshMonitor(grid.getGridMap());;
        }else{
            System.out.println("Active Pattern Null");
            activePattern = null;
        }

        animation_mutex.release();
    }

    public void exit(){
        tetris_gameboy_play.stop();
        timer.cancel();
    }

    public void moveRight(){
        if (activePattern==null || gameState==GameState.PAUSE || gameState==GameState.END) return;
        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.RIGHT_SIDE)){
            try {
                animation_mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            grid.scrollMovableToRight();
            displayUnitController.refreshMonitor(grid.getGridMap());
            animation_mutex.release();
        }
    }

    public void moveLeft(){
        if (activePattern==null || gameState==GameState.PAUSE || gameState==GameState.END) return;
        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.LEFT_SIDE)){
            try {
                animation_mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            grid.scrollMovableToLeft();
            displayUnitController.refreshMonitor(grid.getGridMap());
            animation_mutex.release();
        }
    }

    public void moveDown(){
        if (activePattern==null || gameState==GameState.PAUSE || gameState==GameState.END) return;
        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
            try {
                animation_mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            grid.fall();
            displayUnitController.refreshMonitor(grid.getGridMap());
            animation_mutex.release();
        }
    }

    public void moveFreeFall(){
        if (activePattern==null || gameState==GameState.PAUSE || gameState==GameState.END) return;
        while(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.UNDER_SIDE)){
            try {
                animation_mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            grid.fall();
            displayUnitController.refreshMonitor(grid.getGridMap());
            animation_mutex.release();
        }
    }

    public void rotate(){
        if (activePattern==null || gameState==GameState.PAUSE || gameState==GameState.END) return;
        if(collisionDetector.detect(grid.getGridMap(), activePattern, Variables.RIGHT_SIDE)){
            try {
                animation_mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            grid.rotate();
            displayUnitController.refreshMonitor(grid.getGridMap());
            animation_mutex.release();
        }
    }

    public GameState getGameState(){
        return gameState;
    }
}
