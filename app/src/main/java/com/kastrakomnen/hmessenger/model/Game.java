package com.kastrakomnen.hmessenger.model;

import android.util.Log;

import com.kastrakomnen.hmessenger.model.display.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.policy.PolicyChecker;
import com.kastrakomnen.hmessenger.model.set.BrickType;
import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.set.Set;
import com.kastrakomnen.hmessenger.model.set.SetBuilder;
import com.kastrakomnen.hmessenger.model.set.SetGenerator;
import com.kastrakomnen.hmessenger.model.set.modifier.CoinModifier;
import com.kastrakomnen.hmessenger.model.set.modifier.PrimitiveSetModifierFactory;
import com.kastrakomnen.hmessenger.model.set.modifier.SetModifierFactory;
import com.kastrakomnen.hmessenger.model.set.modifier.StarModifier;
import com.kastrakomnen.hmessenger.model.stat.Distribution;
import com.kastrakomnen.hmessenger.model.stat.DistributionTableBuilder;
import com.kastrakomnen.hmessenger.model.stat.DistributionType;
import com.kastrakomnen.hmessenger.model.stat.GameStatCollector;
import com.kastrakomnen.hmessenger.model.stat.PApplier;
import com.kastrakomnen.hmessenger.model.stat.PApplierFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class Game implements GameInputListener, Subscriber{

    private static final String TAG = "{Game}";

    private final DisplayUnitController displayUnitController;
    private final Board board;

    private final ArrayList<GameStateListener> gameStateListeners;
    private final GameStatCollector gameStatCollector;

    private final PolicyChecker policyChecker;

    private PApplier<FormationType, Set> setGenerator;
    private final ArrayList<PApplier<Boolean, Set>> setModifiers;

    private final SetModifierFactory setModifierFactory;

    private GameState gameState;
    private boolean disableInputs;

    public Game(DisplayUnitController displayUnitController){

        this.displayUnitController = displayUnitController;
        this.gameStatCollector = new GameStatCollector();
        this.policyChecker = new PolicyChecker();
        this.board = new Board(20, 10, displayUnitController, gameStatCollector, policyChecker);
        this.gameStateListeners = new ArrayList<>();
        this.setModifiers = new ArrayList<>();
        this.setModifierFactory = new PrimitiveSetModifierFactory();

        disableInputs = true;

        this.gameStatCollector.addScoreListener(BriketContext.getInstance());
        this.gameStatCollector.addComboListener(BriketContext.getInstance());
        this.gameStatCollector.addTimeListener(BriketContext.getInstance());

        gameState = GameState.PRE_START;
    }

    public void loadStage(Stage stage){

        if (gameState != GameState.PRE_START){
            throw new IllegalStateException("game state not allowed this operation");
        }

        setGenerator = new PApplier<>(
                new Distribution<FormationType>(
                        stage.getDistributionType(),
                        stage.getFormationTypes()
                ),
                new SetBuilder()
        );

        ArrayList<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(false);

        setModifiers.add(
                new PApplier<>(
                        new Distribution<Boolean>(
                                DistributionTableBuilder.build(DistributionType.PROB_10, null),
                                booleans
                        ),
                        setModifierFactory.createProduct(BrickType.STAR)
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
    public void onMoveDown(int amount) {

        Log.d(TAG, "{onMoveDown}");

        if (isDisableInputs()) {
            Log.d(TAG, "{onMoveDown} ─ isDisableInputs::true");
            return;
        }

        disableInputs();

        if (board.getActiveSet() == null){
            Log.d(TAG, "Active set null");

            Set set = setGenerator.apply();
            for (PApplier<Boolean, Set> applier : setModifiers) {
                set = applier.apply(set);
            }

            if (board.place(set)){
                enableInputs();
            }else{
                // Game End
                displayUnitController.end();
            }
        }else{
            boolean ret;
            for (int i = 0; i < amount; i++) {
                ret = board.moveDown();
                if (!ret){
                    enableInputs();
                    break;
                }
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

    public GameStatCollector getGameStatCollector() {
        return gameStatCollector;
    }
}
