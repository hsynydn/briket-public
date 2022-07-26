package com.kastrakomnen.hmessenger.logic;

import com.kastrakomnen.hmessenger.R;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

    public int score = 0;
    private static final int sequenceScoreConstant = 104;

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
    private Context context;

    private GameListener gameListener;

    private static Game instance = null;

    public static Game getInstance(Context context) throws IOException {

        if (instance == null){
            instance = new Game(context);
        }

        return instance;
    }

    private Game(Context context)
            throws
            IOException
    {

        this.grid = new Grid();
        this.randomGenerator = new RandomGenerator();
        this.sequenceDetector  = new SequenceDetector(this.grid);
        this.collisionDetector = new CollisionDetector();
        this.activePattern = null;
        this.timer = new Timer();

        gameState = GameState.NOT_STARTED;

        this.context = context;

        service = Executors.newScheduledThreadPool(1);
    }

    public Game setGameListener(GameListener gameListener){
        this.gameListener = gameListener;
        return this;
    }

    public Game setDisplayUnitController(DisplayUnitController displayUnitController){
        this.displayUnitController = displayUnitController;
        return this;
    }

    public Game setHandler(Handler handler){
        this.handler = handler;
        return this;
    }

    public void start(){

        Log.i(TAG, "{start} ─ call start");

        if (gameState == GameState.START){
            Log.i(TAG, "{start} ─ GameState.START");
            return;
        }

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
        Log.i(TAG, "{resume} ─ call resume");
        gameState = GameState.RESUME;
        start();
    }

    public void end(){
        gameState = GameState.END;
    }

    public void destroy(){
        instance = null;
    }

    private void schedCheck(){

        // If this place is set, it means there is no room to create new object
        // End the game
        if(grid.getGridMap().get(6).isSet()){
            Log.i(TAG, "Game Over");
            service.shutdown();
            gameListener.onGameOver();
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
                score += detected_row_indices.size() * sequenceScoreConstant;
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
                    displayUnitController.setScore(score);
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
