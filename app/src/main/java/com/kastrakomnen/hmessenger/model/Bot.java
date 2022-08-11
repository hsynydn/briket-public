package com.kastrakomnen.hmessenger.model;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot implements GameStateListener{

    private static final String TAG = "{Bot}";

    private GameInputListener gameInputListener;
    private BotBehaviour botBehaviour;

    private final ScheduledExecutorService service;
    private Future<?> future;

    public Bot(BotBehaviour botBehaviour){
        this.botBehaviour = botBehaviour;
        service = Executors.newScheduledThreadPool(1);
    }

    public void registerGameInputListener(GameInputListener gameInputListener){
        this.gameInputListener = gameInputListener;
    }

    @Override
    public void onGameStart() {

        Log.i(TAG, "Bot onGameStart called");

        future = service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            gameInputListener.onMoveDown(1);
                        }catch(Exception e){
                            Log.e(TAG, "Exception occurred ─ " + e.toString());
                        }
                    }
                },
                botBehaviour.botStartDelay,
                botBehaviour.commandGenerationPeriod,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void onGamePause() {
        future.cancel(true);
    }

    @Override
    public void onGameResume() {
        future = service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            gameInputListener.onMoveDown(1);
                        }catch(Exception e){
                            Log.e(TAG, "Exception occurred ─ " + e.toString());
                        }
                    }
                },
                botBehaviour.botStartDelay,
                botBehaviour.commandGenerationPeriod,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void onGameStop() {
        service.shutdown();
    }
}
