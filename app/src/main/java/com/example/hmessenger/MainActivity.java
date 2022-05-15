package com.example.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.hmessenger.logic.DisplayUnitController;
import com.example.hmessenger.logic.Game;
import com.example.hmessenger.logic.Music;

import java.io.IOException;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public PlayBoardView    playBoardView;
    DisplayUnitController   displayUnitController;
    Game                    game;

    Handler handler = new Handler();

    private Music audio_fx_btn_click_3;

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

        playBoardView = findViewById(R.id.view_playground);
        displayUnitController = new DisplayUnitController(this, playBoardView, handler);

        try {
            game = new Game(this, displayUnitController, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable free_fall = new Runnable() {
            @Override
            public void run() {
                game.moveFreeFall();
            }
        };

        Runnable leftX1 = new Runnable() {
            @Override
            public void run() {
                game.moveLeft();
            }
        };

        Runnable leftX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.moveLeft();
                }
            }
        };

        Runnable leftX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.moveLeft();
                }
            }
        };

        Runnable leftX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<6; i++){
                    game.moveLeft();
                }
            }
        };

        Runnable rightX1 = new Runnable() {
            @Override
            public void run() {
                game.moveRight();
            }
        };

        Runnable rightX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.moveRight();
                }
            }
        };

        Runnable rightX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.moveRight();
                }
            }
        };

        Runnable rightX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<6; i++){
                    game.moveRight();
                }
            }
        };

        Runnable fallX1 = new Runnable() {
            @Override
            public void run() {
                game.moveDown();
            }
        };

        Runnable fallX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.moveDown();
                }
            }
        };

        Runnable fallX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.moveDown();
                }
            }
        };

        Runnable fallX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<12; i++){
                    game.moveDown();
                }
            }
        };

        try {
            this.audio_fx_btn_click_3 = new Music(getApplicationContext(), R.raw.audio_fx_btn_click_3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.btn_left_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
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

        findViewById(R.id.btn_right_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
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

        findViewById(R.id.btn_drop).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.control_button_pressed));
                    handler.postDelayed(fallX1, 10);
                    handler.postDelayed(fallX1, 100);
                    handler.postDelayed(fallX2, 200);
                    handler.postDelayed(fallX3, 300);
                    handler.postDelayed(fallX6, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.control_button_no_press));
                    handler.removeCallbacks(fallX1);
                    handler.removeCallbacks(fallX2);
                    handler.removeCallbacks(fallX3);
                    handler.removeCallbacks(fallX6);
                }
                return true;
            }
        });

        findViewById(R.id.btn_rotate).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.control_button_pressed));
                    game.rotate();
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.control_button_no_press));
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

    public void openPlayGround(){
        Intent intent = new Intent(this, ActivityGameSplash.class);
        startActivity(intent);
    }
}