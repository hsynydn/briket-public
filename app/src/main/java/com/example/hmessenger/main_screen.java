package com.example.hmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hmessenger.logic.Cell;
import com.example.hmessenger.logic.DisplayUnitController;
import com.example.hmessenger.logic.Game;
import com.example.hmessenger.logic.PatternType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.net.ssl.SNIHostName;

public class main_screen extends AppCompatActivity
{

    private static final String TAG = "main_screen";
    int color = 0x000000;

    DisplayUnitController displayUnitController;
    Game game;
    Handler handler = new Handler();

    ArrayList<LinearLayout> verticalLayoutArray = new ArrayList<>();
    ArrayList<ImageView> gridPane = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_ground);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        LinearLayout layout;

        layout = findViewById(R.id.layout_vertical_0);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_1);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_2);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_3);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_4);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_5);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_6);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_7);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_8);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_9);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_10);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_11);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_12);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_13);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_14);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_15);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_16);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_17);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_18);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_19);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_20);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_21);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_22);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        layout = findViewById(R.id.layout_vertical_23);
        for (int i=0; i<layout.getChildCount(); i++){
            gridPane.add((ImageView) layout.getChildAt(i));
        }

        displayUnitController = new DisplayUnitController(this, gridPane, findViewById(R.id.next_pattern));

        try {
            game = new Game(this, displayUnitController, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        findViewById(R.id.btn_left_move).setOnClickListener(view -> {
            Log.i(TAG, "btn_left_move Click");
            game.moveLeft();
//            vibrator.vibrate(50);
        });

        findViewById(R.id.btn_right_move).setOnClickListener(view -> {
            Log.i(TAG, "btn_right_move Click");
            game.moveRight();
//            vibrator.vibrate(50);
        });

        findViewById(R.id.btn_drop).setOnClickListener(view -> {
            Log.i(TAG, "btn_drop Click");
            game.moveDown();
//            vibrator.vibrate(50);
        });

        findViewById(R.id.btn_drop).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                game.moveFreeFall();
                return false;
            }
        });

        findViewById(R.id.btn_rotate).setOnClickListener(view -> {
            Log.i(TAG, "btn_rotate Click");
            game.rotate();
//            vibrator.vibrate(50);
        });

        findViewById(R.id.btn_menu).setOnClickListener(view -> {
            Log.i(TAG, "btn_menu Click");
            game.pause();

            Intent intent = new Intent(this, main_menu.class);
//            startActivityForResult(intent, 0);
            startActivity(intent);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}