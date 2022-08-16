package com.kastrakomnen.hmessenger.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.hdroid.PlayBoardView;
import com.kastrakomnen.hmessenger.model.BasePublisher;
import com.kastrakomnen.hmessenger.model.Bot;
import com.kastrakomnen.hmessenger.model.BotBehaviour;
import com.kastrakomnen.hmessenger.model.BriketContext;
import com.kastrakomnen.hmessenger.model.Game;
import com.kastrakomnen.hmessenger.model.GameState;
import com.kastrakomnen.hmessenger.model.Position;
import com.kastrakomnen.hmessenger.model.StageStatus;
import com.kastrakomnen.hmessenger.model.Subscriber;
import com.kastrakomnen.hmessenger.model.display.DisplayData;
import com.kastrakomnen.hmessenger.model.display.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.set.Brick;

import java.util.ArrayList;
import java.util.Objects;

public class ActivityPlayBoard extends AppCompatActivity implements DisplayUnitController{

    private static final String TAG = "{PlayScreen}";

    private BasePublisher basePublisher;
    private Game game;
    private Bot bot;
    private Handler handler;

    /* Views belongs to this activity */
    private PlayBoardView playBoardView;

    private FragmentPause fragmentPause;
    private FragmentOptions fragmentOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_play_board);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        Log.d(TAG, "onViewCreated called");

        fragmentPause = new FragmentPause();
        fragmentOptions = new FragmentOptions();

        handler = new Handler();
        basePublisher = new BasePublisher();

        playBoardView = findViewById(R.id.view_playground);

        playBoardView.create(19, 9);

        /* Game will become a bridge between commands logic and visual */
        game = new Game(this);
        game.loadStage(BriketContext.getInstance().getCurrentStage());

        BotBehaviour botBehaviour = new BotBehaviour(500, 500);
        bot = new Bot(botBehaviour);
        /* Bot can send its own command over this interface to the Game */
        bot.registerGameInputListener(game);

        /* Game notify Bot whenever a GameState changed */
        game.registerGameStateListener(bot);

        /* DisplayUnitController will notify game when finish visual updates */
        this.register(game);

        setUpControls();
    }

    private void setUpControls() {

        Runnable leftX1 = new Runnable() {
            @Override
            public void run() {
                game.onMoveLeft(1);
            }
        };

        Runnable rightX1 = new Runnable() {
            @Override
            public void run() {
                game.onMoveRight(1);
            }
        };

        Runnable fallX1 = new Runnable() {
            @Override
            public void run() {
                game.onMoveDown(1);
            }
        };

        findViewById(R.id.btn_rotate).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    game.onRotate();
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.button_unpressed));
                }
                return true;
            }
        });

        findViewById(R.id.btn_drop).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    handler.postDelayed(fallX1, 10);
                    for (int i = 1; i < 22; i++) {
                        handler.postDelayed(fallX1, 100 * i);
                    }
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.button_unpressed));
                    for (int i = 1; i < 22; i++) {
                        handler.removeCallbacks(fallX1);
                    }
                }
                return true;
            }
        });

        findViewById(R.id.btn_right_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    handler.postDelayed(rightX1, 10);
                    for (int i = 1; i < 10; i++) {
                        handler.postDelayed(rightX1, 100 * i);
                    }
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    for (int i = 1; i < 10; i++) {
                        handler.removeCallbacks(rightX1);
                    }
                }
                return true;
            }
        });

        findViewById(R.id.btn_left_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    handler.postDelayed(leftX1, 10);
                    for (int i = 1; i < 10; i++) {
                        handler.postDelayed(leftX1, 100 * i);
                    }
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    for (int i = 1; i < 10; i++) {
                        handler.removeCallbacks(leftX1);
                    }
                }
                return true;
            }
        });

        findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "menu button clicked");
                onPause();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (game.getGameState() == GameState.PRE_START){
            Log.d(TAG, "onStart called");
            game.start();
            BriketContext.getInstance().MUSIC.play(BriketContext.getInstance().MUSIC.game_play);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(game.getGameState() == GameState.PAUSE){
            Log.d(TAG, "onResume called");
            game.resume();
            game.enableInputs();
            BriketContext.getInstance().MUSIC.resume(BriketContext.getInstance().MUSIC.game_play);
        }else{
            Log.d(TAG, "onResume not called called");
        }
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause called");
        super.onPause();

        Log.d(TAG, game.getGameState().toString());

        if (game.getGameState() == GameState.START || game.getGameState() == GameState.RESUME){
            game.pause();

            BriketContext.getInstance().MUSIC.pause(BriketContext.getInstance().MUSIC.game_play);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, FragmentPause.class, null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        game.disableInputs();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy called");
        super.onDestroy();
        game.stop();
        BriketContext.getInstance().MUSIC.stop(BriketContext.getInstance().MUSIC.game_play);
    }

    @Override
    public void create(DisplayData.Board board) {

    }

    @Override
    public void create(DisplayData.Brick brick, Position at) {

    }

    @Override
    public void move(DisplayData.Brick brick, Position from, Position to) {

    }

    @Override
    public void refresh(ArrayList<ArrayList<Brick>> board) {
        playBoardView.refresh(board);
        publish();
    }

    @Override
    public void move(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> fromPositions, ArrayList<Position> toPositions) {

    }

    @Override
    public void rotate(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> fromPositions, ArrayList<Position> toPositions) {

    }

    @Override
    public void remove(DisplayData.Brick brick, Position at) {

    }

    @Override
    public void removeAndRefresh(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> atPositions, ArrayList<Position> board) {

    }

    @Override
    public void refresh(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> atPositions) {

    }

    @Override
    public void startFast() {

    }

    @Override
    public void startDelay(int delay) {

    }

    @Override
    public void updateInfo() {

    }

    @Override
    public void end(DisplayData.Status status) {

        if (status.stageStatus == StageStatus.GAME_OVER_FAIL){
            Log.d(TAG, "Game ended");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_left, R.anim.to_right, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.fragment_container, FragmentGameOverSummary.class, null);
            fragmentTransaction.commit();
        }else if(status.stageStatus == StageStatus.GAME_OVER_SUCCESS){
            Log.d(TAG, "Game ended");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.from_left, R.anim.to_right, R.anim.from_left, R.anim.to_right);
            fragmentTransaction.replace(R.id.fragment_container, FragmentGameOverSuccess.class, null);
            fragmentTransaction.commit();
        }else{
            throw new IllegalArgumentException("DisplayData.Status unknown");
        }

    }

    @Override
    public void gainScore(ArrayList<DisplayData.Score> scores) {
        playBoardView.popUpScore(scores);
        publish();
    }

    @Override
    public void updateObjective(int objective) {
        playBoardView.updateObjective(Integer.toString(objective));
        publish();
    }

    @Override
    public void setScore(int score) {
        playBoardView.updateScore(Integer.toString(score));
        publish();
    }

    @Override
    public void popUp(DisplayData.PopUpEvent popUpEvent) {
        playBoardView.popUp(popUpEvent);
        publish();
    }

    @Override
    public void register(Subscriber subscriber) {
        basePublisher.register(subscriber);
    }

    @Override
    public void publish() {
        basePublisher.publish();
    }
}