package com.kastrakomnen.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Objects;

public class ActivityPause extends AppCompatActivity {

    public static final String TAG = "ActivityPause";

    private Animation logoBreathingAnimation;

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

        this.logoBreathingAnimation = AnimationUtils.loadAnimation(this, R.anim.breathing);
        findViewById(R.id.layout_pause_iv_logo).setAnimation(logoBreathingAnimation);

        findViewById(R.id.layout_pause_tv_resume).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    Intent pgActivity = new Intent(ActivityPause.this, ActivityPlayScreen.class);
                    pgActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(pgActivity);
                }
                return true;
            }
        });

        findViewById(R.id.layout_pause_tv_restart).setOnClickListener(view -> {
            Log.i(TAG, "btn_new_game");
            Intent intent = new Intent(this, ActivityPlayScreen.class);
            startActivity(intent);
            this.finish();
        });

        findViewById(R.id.layout_pause_tv_quit).setOnClickListener(view -> {
            Log.i(TAG, "pause_btn_exit");
            this.finishAffinity();
        });

        findViewById(R.id.layout_pause_iv_settings).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageView)view).setBackground(getResources().getDrawable(R.drawable.settings_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageView)view).setBackground(getResources().getDrawable(R.drawable.settings_unpressed));
                }
                return true;
            }
        });

        findViewById(R.id.layout_pause_iv_home).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageView)view).setBackground(getResources().getDrawable(R.drawable.home_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageView)view).setBackground(getResources().getDrawable(R.drawable.home_unpressed));
                }
                return true;
            }
        });
    }
}