package com.example.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hmessenger.logic.DisplayUnitController;
import com.example.hmessenger.logic.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
        setContentView(R.layout.layout_play_board);

        android.util.DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;

        Log.i(TAG, "heightPixels ── " + heightPixels); // 2131
        Log.i(TAG, "widthPixels ── " + widthPixels); // 1080

        double height_coefficient = heightPixels / 35.0;
        Log.i(TAG, "height_coefficient ── " + height_coefficient); //
        double width_coefficient  = widthPixels / 16.0;
        Log.i(TAG, "width_coefficient ── " + width_coefficient); //

        if (height_coefficient < width_coefficient){
            int new_height = (int)height_coefficient * 24;
            int new_width = (int)height_coefficient * 12;

            LinearLayout linearLayout = findViewById(R.id.layout_play_board);

            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.height = new_height;
            layoutParams.width = new_width;

            Log.i(TAG, "Root height ── " + new_height);
            Log.i(TAG, "Root width ── " + new_width);

            linearLayout.setLayoutParams(layoutParams);

//            LinearLayout bottomCtrlMiddleLayout = findViewById(R.id.bottom_ctrl_middle);
//            ViewGroup.LayoutParams bottomCtrlMiddleLayoutParams = bottomCtrlMiddleLayout.getLayoutParams();
//            bottomCtrlMiddleLayoutParams.width = new_width;
//            bottomCtrlMiddleLayout.setLayoutParams(bottomCtrlMiddleLayoutParams);
//
//            LinearLayout bottomCtrlLeftLayout = findViewById(R.id.bottom_ctrl_left);
//            ViewGroup.LayoutParams bottomCtrlLeftLayoutParams = bottomCtrlLeftLayout.getLayoutParams();
//            bottomCtrlLeftLayoutParams.width = (widthPixels - new_width) / 2;
//            bottomCtrlLeftLayout.setLayoutParams(bottomCtrlLeftLayoutParams);
//
//            LinearLayout bottomCtrlRightLayout = findViewById(R.id.bottom_ctrl_right);
//            ViewGroup.LayoutParams bottomCtrlRightLayoutParams = bottomCtrlRightLayout.getLayoutParams();
//            bottomCtrlRightLayoutParams.width = (widthPixels - new_width) / 2;
//            bottomCtrlRightLayout.setLayoutParams(bottomCtrlRightLayoutParams);

//            LinearLayout PGBodyLeftLayout = findViewById(R.id.PG_body_left);
//            ViewGroup.LayoutParams PGBodyLeftLayoutParams = PGBodyLeftLayout.getLayoutParams();
//            PGBodyLeftLayoutParams.width = (widthPixels - new_width) / 2;
//            PGBodyLeftLayout.setLayoutParams(PGBodyLeftLayoutParams);
//
//            LinearLayout PGBodyRightLayout = findViewById(R.id.PG_body_right);
//            ViewGroup.LayoutParams PGBodyRightLayoutParams = PGBodyRightLayout.getLayoutParams();
//            PGBodyRightLayoutParams.width = (widthPixels - new_width) / 2;
//            PGBodyRightLayout.setLayoutParams(PGBodyRightLayoutParams);
        }else{
            int new_height = (int)width_coefficient * 24;
            int new_width = (int)width_coefficient * 12;

            LinearLayout linearLayout = findViewById(R.id.layout_play_board);

            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.height = new_height;
            layoutParams.width = new_width;

            Log.i(TAG, "Root height ── " + new_height);
            Log.i(TAG, "Root width ── " + new_width);

            linearLayout.setLayoutParams(layoutParams);

//            LinearLayout bottomCtrlMiddleLayout = findViewById(R.id.bottom_ctrl_middle);
//            ViewGroup.LayoutParams bottomCtrlMiddleLayoutParams = bottomCtrlMiddleLayout.getLayoutParams();
//            bottomCtrlMiddleLayoutParams.width = new_width;
//            bottomCtrlMiddleLayout.setLayoutParams(bottomCtrlMiddleLayoutParams);
//
//            LinearLayout bottomCtrlLeftLayout = findViewById(R.id.bottom_ctrl_left);
//            ViewGroup.LayoutParams bottomCtrlLeftLayoutParams = bottomCtrlLeftLayout.getLayoutParams();
//            bottomCtrlLeftLayoutParams.width = (widthPixels - new_width) / 2;
//            bottomCtrlLeftLayout.setLayoutParams(bottomCtrlLeftLayoutParams);
//
//            LinearLayout bottomCtrlRightLayout = findViewById(R.id.bottom_ctrl_right);
//            ViewGroup.LayoutParams bottomCtrlRightLayoutParams = bottomCtrlRightLayout.getLayoutParams();
//            bottomCtrlRightLayoutParams.width = (widthPixels - new_width) / 2;
//            bottomCtrlRightLayout.setLayoutParams(bottomCtrlRightLayoutParams);

//            LinearLayout PGBodyLeftLayout = findViewById(R.id.PG_body_left);
//            ViewGroup.LayoutParams PGBodyLeftLayoutParams = PGBodyLeftLayout.getLayoutParams();
//            PGBodyLeftLayoutParams.width = (widthPixels - new_width) / 2;
//            PGBodyLeftLayout.setLayoutParams(PGBodyLeftLayoutParams);
//
//            LinearLayout PGBodyRightLayout = findViewById(R.id.PG_body_right);
//            ViewGroup.LayoutParams PGBodyRightLayoutParams = PGBodyRightLayout.getLayoutParams();
//            PGBodyRightLayoutParams.width = (widthPixels - new_width) / 2;
//            PGBodyRightLayout.setLayoutParams(PGBodyRightLayoutParams);

        }

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

//        displayUnitController = new DisplayUnitController(this, gridPane, findViewById(R.id.next_pattern), handler);

        try {
            game = new Game(this, displayUnitController, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        findViewById(R.id.btn_left_move).setOnClickListener(view -> {
            Log.i(TAG, "btn_left_move Click");
            game.moveLeft();
        });

        findViewById(R.id.btn_right_move).setOnClickListener(view -> {
            Log.i(TAG, "btn_right_move Click");
            game.moveRight();
        });

        Runnable free_fall = new Runnable() {
            @Override
            public void run() {
                game.moveFreeFall();
            }
        };

        findViewById(R.id.btn_drop).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.control_button_pressed));
                    game.moveDown();
                    handler.postDelayed(free_fall, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.control_button_no_press));
                    handler.removeCallbacks(free_fall);
                }
                return true;
            }
        });

        findViewById(R.id.btn_rotate).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
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

//    @Override
//    protected void onResume() {
//        super.onResume();
////        game.resume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
////        game.pause();
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}