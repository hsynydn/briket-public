package com.kastrakomnen.hmessenger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Objects;

import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;
import com.kastrakomnen.hmessenger.view.Recycler;

public class ActivityHome extends AppCompatActivity {

    private static final String TAG = "activity_main_menu";

    private Animation animaton;
    private Animation animaton_2;
    private Animation animaton_3;

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

        animaton = AnimationUtils.loadAnimation(this, R.anim.dropping_bricks);
        ImageView drop_1 = findViewById(R.id.drop_1);
        drop_1.setAnimation(animaton);

        animaton_2 = AnimationUtils.loadAnimation(this, R.anim.dropping_bricks_2);
        ImageView drop_2 = findViewById(R.id.drop_2);
        drop_2.setAnimation(animaton_2);

        animaton_3 = AnimationUtils.loadAnimation(this, R.anim.dropping_bricks_3);
        ImageView drop_3 = findViewById(R.id.iv_home_drop_3);
        drop_3.setAnimation(animaton_3);

        findViewById(R.id.home_btn_new_game).setOnClickListener(view -> {
            Log.i(TAG, "Play");
            Intent intent = new Intent(this, ActivityGameMap.class);
            startActivity(intent);
            this.finish();
        });

        findViewById(R.id.home_btn_options).setOnClickListener(view -> {
            Log.i(TAG, "btn_home_options");
            Intent intent = new Intent(this, ActivityOptions.class);
            startActivity(intent);
            this.finish();
        });

        findViewById(R.id.home_pause_btn_exit).setOnClickListener(view -> {
            Log.i(TAG, "Exit");
            this.finishAffinity();
        });

        PlayGamesSdk.initialize(this);

        GamesSignInClient gamesSignInClient = PlayGames.getGamesSignInClient(this);

        gamesSignInClient.isAuthenticated().addOnCompleteListener(isAuthenticatedTask -> {
            boolean isAuthenticated =
                    (isAuthenticatedTask.isSuccessful() &&
                            isAuthenticatedTask.getResult().isAuthenticated());

            if (isAuthenticated) {
                // Continue with Play Games Services
            } else {
                // Disable your integration with Play Games Services or show a
                // login button to ask  players to sign-in. Clicking it should
                // call GamesSignInClient.signIn().
            }
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