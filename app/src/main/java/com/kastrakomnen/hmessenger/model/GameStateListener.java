package com.kastrakomnen.hmessenger.model;

public interface GameStateListener {
    public void onGameStart();
    public void onGamePause();
    public void onGameResume();
    public void onGameStop();
}
