package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public class Game implements GameInputListener {

    private DisplayUnitController displayUnitController;
    private Board board;
    private SetGenerator setGenerator;
    private final ArrayList<GameStateListener> gameStateListeners;

    private GameState gameState;
    private final boolean disableInputs;

    public Game(DisplayUnitController displayUnitController){

        this.displayUnitController = displayUnitController;
        this.board = new Board(24, 12);
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

        Set set = setGenerator.generate();
//        board.

        gameState = GameState.START;
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
    public void onMoveRight() {
        if (disableInputs) return;

    }

    @Override
    public void onMoveLeft() {
        if (disableInputs) return;

    }

    @Override
    public void onMoveDown() {
        if (disableInputs) return;

        if (board.getActiveSet() == null){
            board.place(setGenerator.generate());
        }

    }

    @Override
    public void onRotate() {
        if (disableInputs) return;

    }

    public void registerGameStateListener(GameStateListener gameStateListener){
        gameStateListeners.add(gameStateListener);
    }
}
