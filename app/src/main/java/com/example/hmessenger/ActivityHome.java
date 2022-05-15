package com.example.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.Objects;

public class ActivityHome extends AppCompatActivity {

    private static final String TAG = "activity_main_menu";

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

        findViewById(R.id.btn_new_game).setOnClickListener(view -> {
            Log.i(TAG, "New Game");
            Intent intent = new Intent(this, ActivityPlayScreen.class);
            startActivity(intent);
            this.finish();
        });

        findViewById(R.id.btn_exit).setOnClickListener(view -> {
            Log.i(TAG, "Exit");
            this.finishAffinity();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
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