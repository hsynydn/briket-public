package com.kastrakomnen.hmessenger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.BriketContext;

public class ActivityHome extends AppCompatActivity {

    private static final String TAG = "{view.ActivityHome}";

    private Animation animaton;
    private Animation animaton_2;
    private Animation animaton_3;
    private Animation logoBreathingAnimation;
    private Animation playFrameHorizontalBreathingAnimation;

    private ImageView drop_2;
    private ImageView drop_3;

    private ProgressBar progressBar;
    private TextView loading;
    private ImageView layout_home_iv_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_Hmessenger);
        setContentView(R.layout.layout_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        animaton_2 = AnimationUtils.loadAnimation(this, R.anim.dropping_bricks_2);
        drop_2 = findViewById(R.id.drop_2);

        animaton_3 = AnimationUtils.loadAnimation(this, R.anim.dropping_bricks_3);
        drop_3 = findViewById(R.id.iv_home_drop_3);

        this.logoBreathingAnimation = AnimationUtils.loadAnimation(this, R.anim.breathing);
        findViewById(R.id.iv_logo).setAnimation(logoBreathingAnimation);

        this.playFrameHorizontalBreathingAnimation = AnimationUtils.loadAnimation(this, R.anim.horizontal_breathing);
        findViewById(R.id.layout_home_iv_play).setAnimation(playFrameHorizontalBreathingAnimation);

//        findViewById(R.id.home_pause_btn_exit).setOnClickListener(view -> {
//            Log.i(TAG, "Exit");
//            this.finishAffinity();
//        });

        layout_home_iv_play = findViewById(R.id.layout_home_iv_play);
        layout_home_iv_play.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i(TAG, "Play button pressed");
                Intent intent = new Intent(ActivityHome.this, ActivityGameMap.class);
                startActivity(intent);
                ActivityHome.this.finish();
                return true;
            }
        });

        layout_home_iv_play.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.lout_home_textview_loading);

        drop_2.setAnimation(animaton_2);
        drop_3.setAnimation(animaton_3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "BriketContext.loading started");
                BriketContext.getInstance().initializeDatabase(ActivityHome.this);
                BriketContext.getInstance().initializeAdMob(ActivityHome.this);
                BriketContext.getInstance().initializeGooglePlayServices(ActivityHome.this);
                Log.d(TAG, "BriketContext.loading finished");

                SystemClock.sleep(2000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        loading.setVisibility(View.INVISIBLE);
                        layout_home_iv_play.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}