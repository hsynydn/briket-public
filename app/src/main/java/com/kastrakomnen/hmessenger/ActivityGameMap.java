package com.kastrakomnen.hmessenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kastrakomnen.hmessenger.activity.PlayScreen;
import com.kastrakomnen.hmessenger.db.BriketDatabase;
import com.kastrakomnen.hmessenger.db.entity.BotBehaviour;
import com.kastrakomnen.hmessenger.db.entity.Preferences;
import com.kastrakomnen.hmessenger.db.entity.Stage;
import com.kastrakomnen.hmessenger.view.ItemClickListener;
import com.kastrakomnen.hmessenger.view.ProgressCard;
import com.kastrakomnen.hmessenger.view.ProgressCardAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityGameMap extends AppCompatActivity implements ItemClickListener {

    private static final String TAG = "{ActivityGameMap}";

    private MediaPlayer audio_fx_button_settings;
    private AdView adView;
    private Animation logoBreathingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_Hmessenger);
        setContentView(R.layout.layout_game_map);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<BotBehaviour> botBehaviours = BriketDatabase.getInstance(ActivityGameMap.this).getBotBehaviourDAO().getBotBehaviours();
                Log.d(TAG, "botBehaviours.size() = " + botBehaviours.size());
            }
        });

        this.audio_fx_button_settings = MediaPlayer.create(getApplicationContext(), R.raw.fx_audio_pause);

        this.logoBreathingAnimation = AnimationUtils.loadAnimation(this, R.anim.breathing);
        findViewById(R.id.game_map_iv_logo).setAnimation(logoBreathingAnimation);

        RecyclerView recyclerView = findViewById(R.id.rv_progress_cards);

        List<ProgressCard> progressCardList = new ArrayList<>();
        progressCardList.add(new ProgressCard(false));
        progressCardList.add(new ProgressCard(true));
        progressCardList.add(new ProgressCard(true));
        progressCardList.add(new ProgressCard(true));
        progressCardList.add(new ProgressCard(true));

        ProgressCardAdapter progressCardAdapter = new ProgressCardAdapter(progressCardList);
        progressCardAdapter.setClickListener(this);

        recyclerView.setAdapter(progressCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        findViewById(R.id.iv_settings).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_button_settings.start();
                    ((ImageView)view).setBackground(getResources().getDrawable(R.drawable.gear_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageView)view).setBackground(getResources().getDrawable(R.drawable.gear_unpressed));

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layout_game_map, new FragmentOptions());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(ActivityGameMap.this, PlayScreen.class);
        startActivity(intent);
        ActivityGameMap.this.finish();
    }
}