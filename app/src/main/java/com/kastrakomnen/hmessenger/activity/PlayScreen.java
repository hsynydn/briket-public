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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.Bot;
import com.kastrakomnen.hmessenger.model.BotBehaviour;
import com.kastrakomnen.hmessenger.model.BriketContext;
import com.kastrakomnen.hmessenger.model.DisplayData;
import com.kastrakomnen.hmessenger.model.DisplayUnitController;
import com.kastrakomnen.hmessenger.model.DistributionType;
import com.kastrakomnen.hmessenger.model.FormationType;
import com.kastrakomnen.hmessenger.model.Game;
import com.kastrakomnen.hmessenger.model.Stage;
import com.kastrakomnen.hmessenger.model.WinCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PlayScreen extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "PlayScreen";

    private GestureDetector gestureDetector;

    private Game game;
    private DisplayUnitController displayUnitController;
    private Bot bot;
    private Handler handler;

    private AdView adView;

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

//        adView = findViewById(R.id.playboard_adview);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        handler = new Handler();
        gestureDetector = new GestureDetector(this, new GestureListener());

        /* DisplayUnitController will do all visual tasks */
        displayUnitController = findViewById(R.id.view_playground);
        displayUnitController.create(new DisplayData.Board(20, 10));

        /* Game will become a bridge between commands logic and visual */
        game = new Game(displayUnitController);
        game.loadStage(BriketContext.getInstance().getCurrentStage());

        BotBehaviour botBehaviour = new BotBehaviour(500, 500);
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