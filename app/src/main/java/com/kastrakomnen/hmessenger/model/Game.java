package com.kastrakomnen.hmessenger.model;

import android.util.Log;

import java.util.ArrayList;

public class Game implements GameInputListener, Subscriber{

    private static final String TAG = "Game";

    private DisplayUnitController displayUnitController;
    private Board board;
    private SetGenerator setGenerator;
    private final ArrayList<GameStateListener> gameStateListeners;

    private GameState gameState;
    private boolean disableInputs;

    public Game(DisplayUnitController displayUnitController){

        this.displayUnitController = displayUnitController;
        this.board = new Board(20, 10, displayUnitController);
        this.gameStateListeners = new ArrayList<>();

        disableInputs = true;

        gameState = GameState.PRE_START;
    }

    public void loadStage(Stage stage){

        if (gameState != GameState.PRE_START){
            throw new IllegalStateException("game state not allowed this operation");
        }

        setGenerator = new SetGenerator(
                new Distribution<FormationType>(
                        stage.getDistributionType(),
                        stage.getFormationTypes()
                )
        );

    }

    public void start(){
        gameState = GameState.START;
        disableInputs = false;
        for (GameStateListener listener: gameStateListeners) {
            listener.onGameStart();
        }
    }

    public void pause(){
        gameState = GameState.PAUSE;
        for (GameStateListener listener: gameStateListeners) {
            listener.onGamePause();
        }
    }

    public void resume(){
        gameState = GameState.RESUME;
        for (GameStateListener listener: gameStateListeners) {
            listener.onGameResume();
        }
    }

    public void stop(){
        gameState = GameState.STOP;
        for (GameStateListener listener: gameStateListeners) {
            listener.onGameStop();
        }
    }

    public GameState getGameState(){
        return gameState;
    }

    @Override
    public void onMoveRight(int amount) {

        Log.d(TAG, "{onMoveRight}");

        if (isDisableInputs()) {
            Log.d(TAG, "{onMoveRight} ─ isDisableInputs::true");
            return;
        }

        disableInputs();

        if (board.getActiveSet() != null) {
            Log.d(TAG, "{onMoveRight} ─ activeSet::notnull");

            boolean ret;
            for (int i = 0; i < amount; i++) {
                ret = board.moveRight();
                if (!ret){
                    enableInputs();
                    break;
                }
            }

            return;
        }

        enableInputs();
    }

    @Override
    public void onMoveLeft(int amount) {

        Log.d(TAG, "{onMoveLeft}");

        if (isDisableInputs()) {
            Log.d(TAG, "{onMoveLeft} ─ isDisableInputs::true");
            return;
        }

        disableInputs();

        if (board.getActiveSet() != null) {

            boolean ret;
            for (int i = 0; i < amount; i++) {
                ret = board.moveLeft();
                if (!ret){
                    enableInputs();
                    break;
                }
            }

            return;
        }

        enableInputs();
    }

    @Override
    public void onMoveDown() {

        Log.d(TAG, "{onMoveDown}");

        if (isDisableInputs()) {
            Log.d(TAG, "{onMoveDown} ─ isDisableInputs::true");
            return;
        }

        disableInputs();

        if (board.getActiveSet() == null){
            Log.d(TAG, "Active set null");
            if (board.place(setGenerator.generate())){
                enableInputs();
            }
        }else{
            if (!board.moveDown()) {
                enableInputs();
            }
        }
    }

    @Override
    public void onRotate() {

        Log.d(TAG, "{onRotate}");

        if (isDisableInputs()) {
            Log.d(TAG, "{onRotate} ─ isDisableInputs::true");
            return;
        }

        disableInputs();

        if (board.getActiveSet() != null) {
            if (!board.rotateCW()){
                Log.d(TAG, "{onRotate} ── board.rotateCW failed ─ reactivate inputs");
                enableInputs();
            }
            return;
        }

        enableInputs();
    }

    public void registerGameStateListener(GameStateListener gameStateListener){
        gameStateListeners.add(gameStateListener);
    }

    private synchronized void toggleInputs(){
        disableInputs = !disableInputs;
    }

    private synchronized void disableInputs(){
        disableInputs = true;
    }

    private synchronized boolean isDisableInputs(){
        return disableInputs;
    }

    private synchronized void enableInputs(){
        disableInputs = false;
    }

    @Override
    public void onNotify() {
        Log.i(TAG, "onNotify called");
        enableInputs();
    }
}
