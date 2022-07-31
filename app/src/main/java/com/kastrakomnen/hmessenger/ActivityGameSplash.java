package com.kastrakomnen.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Objects;

public class ActivityGameSplash extends AppCompatActivity
{

    private static final String TAG = "ActivityGameSplash";

    private Animation animaton;
    private Animation animation_logo_text_show;
    ImageView imageView;
    ImageView splash_iv_logo_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        animaton = AnimationUtils.loadAnimation(this, R.anim.splash);
        animation_logo_text_show = AnimationUtils.loadAnimation(this, R.anim.splash_logo_text_show);

        imageView = findViewById(R.id.logo_splash);
        splash_iv_logo_text = findViewById(R.id.splash_iv_logo_text);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ActivityGameSplash.this, ActivityHome.class));
                finish();
            }
        }, 2500);
    }

    @Override
    protected void onStart() {
        super.onStart();

        imageView.setAnimation(animaton);
        splash_iv_logo_text.setAnimation(animation_logo_text_show);
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