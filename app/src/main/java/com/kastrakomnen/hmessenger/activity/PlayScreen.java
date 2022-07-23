package com.kastrakomnen.hmessenger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.hdroid.PlayBoardView;
import com.kastrakomnen.hmessenger.model.Bot;
import com.kastrakomnen.hmessenger.model.BotBehaviour;
import com.kastrakomnen.hmessenger.model.DisplayData;
import com.kastrakomnen.hmessenger.model.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.DistributionTableBuilder;
import com.kastrakomnen.hmessenger.model.DistributionType;
import com.kastrakomnen.hmessenger.model.FormationType;
import com.kastrakomnen.hmessenger.model.Game;
import com.kastrakomnen.hmessenger.model.RelativePosition;
import com.kastrakomnen.hmessenger.model.Stage;
import com.kastrakomnen.hmessenger.model.WinCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayScreen extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "PlayScreen";

    private GestureDetector gestureDetector;

    private Game game;
    private DisplayUnitController displayUnitController;
    private Bot bot;
    private Handler handler;

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
        gestureDetector = new GestureDetector(this, new GestureListener());

        /* DisplayUnitController will do all visual tasks */
        displayUnitController = findViewById(R.id.view_playground);
        displayUnitController.create(new DisplayData.Board(20, 10));

        Stage stage = new Stage();
        stage.setDistributionType(DistributionType.UNIFORM);
        stage.setFormationTypes(new ArrayList<>(Arrays.asList(
                FormationType.BOX_CW0, // trace on rotate bug
                FormationType.T_CW180, // sometimes down head stuck at up
                FormationType.Z_CW270, // trace on rotate bug
                FormationType.LINE_CW90, // trace on rotate bug
                FormationType.RL_CW90, // trace on rotate bug
                FormationType.L_CW90
                )));
        stage.setWinCondition(WinCondition.AGAINST_TIME);

        /* Game will become a bridge between commands logic and visual */
        game = new Game(displayUnitController);
        game.loadStage(stage);

        BotBehaviour botBehaviour = new BotBehaviour(1000, 500);
        bot = new Bot(botBehaviour);
        /* Bot can send its own command over this interface to the Game */
        bot.registerGameInputListener(game);

        /* Game notify Bot whenever a GameState changed */
        game.registerGameStateListener(bot);

        /* DisplayUnitController will notify game when finish visual updates */
        displayUnitController.register(game);

        setUpControls();
    }

    private void setUpControls() {

        Runnable leftX1 = new Runnable() {
            @Override
            public void run() {
                game.onMoveLeft(1);
            }
        };

        Runnable leftX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.onMoveLeft(2);
                }
            }
        };

        Runnable leftX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.onMoveLeft(3);
                }
            }
        };

        Runnable leftX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<6; i++){
                    game.onMoveLeft(6);
                }
            }
        };

        Runnable rightX1 = new Runnable() {
            @Override
            public void run() {
                game.onMoveRight(1);
            }
        };

        Runnable rightX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.onMoveRight(2);
                }
            }
        };

        Runnable rightX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.onMoveRight(3);
                }
            }
        };

        Runnable rightX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<6; i++){
                    game.onMoveRight(6);
                }
            }
        };

        Runnable fallX1 = new Runnable() {
            @Override
            public void run() {
                game.onMoveDown(1);
            }
        };

        Runnable fallX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.onMoveDown(2);
                }
            }
        };

        Runnable fallX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.onMoveDown(3);
                }
            }
        };

        Runnable fallX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<12; i++){
                    game.onMoveDown(12);
                }
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
                    handler.postDelayed(fallX1, 100);
                    handler.postDelayed(fallX1, 10);
                    handler.postDelayed(fallX2, 200);
                    handler.postDelayed(fallX3, 300);
                    handler.postDelayed(fallX6, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    handler.removeCallbacks(fallX1);
                    handler.removeCallbacks(fallX2);
                    handler.removeCallbacks(fallX3);
                    handler.removeCallbacks(fallX6);
                }
                return true;
            }
        });

        findViewById(R.id.btn_right_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    handler.postDelayed(rightX1, 100);
                    handler.postDelayed(rightX1, 10);
                    handler.postDelayed(rightX2, 200);
                    handler.postDelayed(rightX3, 300);
                    handler.postDelayed(rightX6, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    handler.removeCallbacks(rightX1);
                    handler.removeCallbacks(rightX2);
                    handler.removeCallbacks(rightX3);
                    handler.removeCallbacks(rightX6);
                }
                return true;
            }
        });

        findViewById(R.id.btn_left_move).setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                            handler.postDelayed(leftX1, 10);
                            handler.postDelayed(leftX1, 100);
                            handler.postDelayed(leftX2, 200);
                            handler.postDelayed(leftX3, 300);
                            handler.postDelayed(leftX6, 400);
                        }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                            handler.removeCallbacks(leftX1);
                            handler.removeCallbacks(leftX2);
                            handler.removeCallbacks(leftX3);
                            handler.removeCallbacks(leftX6);
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

    private void onSwipeRight(int amount){
        Log.d(TAG, "onSwipeRight");
        game.onMoveRight(amount);
    }

    private void onSwipeLeft(int amount){
        Log.d(TAG, "onSwipeLeft");
        game.onMoveLeft(amount);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_VELOCITY_THRESHOLD = 10;
        private static final int SWIPE_THRESHOLD = 100;

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            game.onMoveRight(1);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            game.onMoveRight(3);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            game.onMoveRight(6);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            return super.onFling(e1, e2, velocityX, velocityY);

            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > 600 && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight(6);
                    } else {
                        onSwipeLeft(6);
                    }
                }else if (Math.abs(diffX) > 500 && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight(5);
                    } else {
                        onSwipeLeft(5);
                    }
                }else if (Math.abs(diffX) > 400 && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight(4);
                    } else {
                        onSwipeLeft(4);
                    }
                }else if (Math.abs(diffX) > 300 && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight(3);
                    } else {
                        onSwipeLeft(3);
                    }
                }
                else if (Math.abs(diffX) > 200 && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight(2);
                    } else {
                        onSwipeLeft(2);
                    }
                }
                else if (Math.abs(diffX) > 100 && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight(1);
                    } else {
                        onSwipeLeft(1);
                    }
                }
            }

            return true;
        }
    }
}