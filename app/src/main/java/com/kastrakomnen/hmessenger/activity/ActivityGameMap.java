package com.kastrakomnen.hmessenger.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.db.BriketDatabase;
import com.kastrakomnen.hmessenger.db.entity.BotBehaviourEntity;
import com.kastrakomnen.hmessenger.model.BriketContext;
import com.kastrakomnen.hmessenger.model.Stage;
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

    private RecyclerView recyclerView;
    private List<ProgressCard> progressCardList;
    private ProgressCardAdapter progressCardAdapter;

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

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<BotBehaviourEntity> botBehaviours = BriketDatabase.getInstance(ActivityGameMap.this).getBotBehaviourDAO().getBotBehaviours();
                Log.d(TAG, "botBehaviours.size() = " + botBehaviours.size());
            }
        });

        this.audio_fx_button_settings = MediaPlayer.create(getApplicationContext(), R.raw.fx_audio_pause);

        this.logoBreathingAnimation = AnimationUtils.loadAnimation(this, R.anim.breathing);
        findViewById(R.id.game_map_iv_logo).setAnimation(logoBreathingAnimation);

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
                    fragmentTransaction.setCustomAnimations(R.anim.drop_down, R.anim.pull_up, R.anim.pull_up, R.anim.pull_up);
                    fragmentTransaction.replace(R.id.layout_game_map, new FragmentPreferencesDropDown());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view, int position) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                BriketContext.getInstance().reinitializeDatabase(ActivityGameMap.this);
            }
        });

        BriketContext.getInstance().setCurrentStage(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit);
        fragmentTransaction.replace(R.id.layout_game_map, new FragmentStageDetails());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        BriketContext.getInstance().mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        BriketContext.getInstance().mInterstitialAd = null;
                    }
                });


        adView = findViewById(R.id.adView);
        adView.loadAd(adRequest);

        recyclerView = findViewById(R.id.rv_progress_cards);
        progressCardList = new ArrayList<>();
        progressCardAdapter = new ProgressCardAdapter(progressCardList);
        progressCardAdapter.setClickListener(this);
        recyclerView.setAdapter(progressCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        for (Stage stage : BriketContext.getInstance().getStages()) {
            ProgressCard progressCard = new ProgressCard();
            progressCard.setLocked(stage.isLocked());
            progressCard.setHighScore(stage.getHighScore());
            progressCard.setIndex(stage.getIndex());
            progressCard.setName(stage.getName());

            progressCardList.add(progressCard);
        }
    }
}