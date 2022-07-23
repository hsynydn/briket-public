package com.kastrakomnen.hmessenger.model;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot implements GameStateListener{

    private static final String TAG = "Bot";

    private GameInputListener gameInputListener;
    private BotBehaviour botBehaviour;

    private final ScheduledExecutorService service;

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

        service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        gameInputListener.onMoveDown(1);
                    }
                },
                botBehaviour.botStartDelay,
                botBehaviour.commandGenerationPeriod,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void onGamePause() {
        service.shutdown();
    }

    @Override
    public void onGameResume() {
        service.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        gameInputListener.onMoveDown(1);
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
