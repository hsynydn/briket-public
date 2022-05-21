package com.example.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.Objects;

public class ActivityPause extends AppCompatActivity {

    public static final String TAG = "ActivityPause";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pause);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        findViewById(R.id.tv_resume).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    startActivity(new Intent(ActivityPause.this, ActivityPlayScreen.class));
//                    finish();
                }
                return true;
            }
        });
    }
}