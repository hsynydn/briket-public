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
import android.widget.TextView;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.hdroid.PlayBoardView;
import com.kastrakomnen.hmessenger.model.BasePublisher;
import com.kastrakomnen.hmessenger.model.Bot;
import com.kastrakomnen.hmessenger.model.BotBehaviour;
import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.BriketContext;
import com.kastrakomnen.hmessenger.model.display.DisplayData;
import com.kastrakomnen.hmessenger.model.display.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.Game;
import com.kastrakomnen.hmessenger.model.Position;
import com.kastrakomnen.hmessenger.model.Subscriber;

import java.util.ArrayList;
import java.util.Objects;

public class PlayScreen extends AppCompatActivity implements DisplayUnitController{

    private static final String TAG = "{PlayScreen}";

    private BasePublisher basePublisher;
    private Game game;
    private Bot bot;
    private Handler handler;

    /* Views belongs to this activity */
    private PlayBoardView playBoardView;
    private TextView textViewScoreContent;

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

        handler = new Handler();
        basePublisher = new BasePublisher();

        playBoardView = findViewById(R.id.view_playground);
        playBoardView.create(20, 10);

        textViewScoreContent = findViewById(R.id.tv_score_content);

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
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.rotate_pressed));
                    game.onRotate();
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.rotate_unpressed));
                }
                return true;
            }
        });

        findViewById(R.id.btn_drop).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    handler.postDelayed(fallX1, 10);
                    for (int i = 1; i < 22; i++) {
                        handler.postDelayed(fallX1, 100 * i);
                    }
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        game.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        game.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        game.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        game.stop();
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
    public void end() {
        Log.d(TAG, "Game ended");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_play_board_root, new FragmentGameOverSummary());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void gainScore(ArrayList<DisplayData.Score> scores) {
        playBoardView.popUpScore(scores);
        publish();
    }

    @Override
    public void setScore(int score) {
        textViewScoreContent.setText(Integer.toString(score));
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