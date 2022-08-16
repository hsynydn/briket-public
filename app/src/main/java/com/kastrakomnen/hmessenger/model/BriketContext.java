package com.kastrakomnen.hmessenger.model;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.db.BriketDatabase;
import com.kastrakomnen.hmessenger.db.entity.FormationEntity;
import com.kastrakomnen.hmessenger.db.entity.PreferencesEntity;
import com.kastrakomnen.hmessenger.db.entity.StageEntity;
import com.kastrakomnen.hmessenger.db.entity.intermediate.WinConditionIntermediateData;
import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.stat.DistributionType;
import com.kastrakomnen.hmessenger.model.stat.GameStatCollector;
import com.kastrakomnen.hmessenger.model.stat.GameStatistics;

import java.util.ArrayList;
import java.util.List;

public class BriketContext implements GameStatCollector.ScoreListener, GameStatCollector.ComboListener, GameStatCollector.TimeListener {

    private static final String TAG = "{BriketContext}";

    private static BriketContext instance;

    private final ArrayList<Stage> stages;
    private final Preferences preferences;
    private GameStatistics gameStatistics;

    private Stage currentStage;

    public Music music = new Music();
    public Sound sound = new Sound();

    public InterstitialAd mInterstitialAd;

    private BriketContext(){
        stages = new ArrayList<>();
        preferences = new Preferences();
        gameStatistics = new GameStatistics();
        music = new Music();
        sound = new Sound();
    }

    public static BriketContext getInstance() {
        if (instance == null){
            instance = new BriketContext();
        }

        return instance;
    }

    public void initializeSound(Context context){
        sound.load(context);
        music.load(context);
    }

    public void initializeDatabase(Context context){

        BriketDatabase db = BriketDatabase.getInstance(context);

        for (StageEntity dbStage : db.getStageDAO().getStages()) {

            Stage stage = new Stage();

            /* Set Up Formation Types */
            ArrayList<FormationType> formationTypes = new ArrayList<>();
            for (FormationEntity dbFormation : db.getStageDAO().getFormations(dbStage.id)) {
                FormationType.valueOf(dbFormation.name).setFormationToughness(dbFormation.toughness);
                formationTypes.add(FormationType.valueOf(dbFormation.name));
            }
            stage.setFormationTypes(formationTypes);

            /* Set Up Win Conditions */
            ArrayList<WinCondition> winConditions = new ArrayList<>();
            for (WinConditionIntermediateData winCondition: db.getStageDAO().getWinConditionDetails(dbStage.id)) {
                winConditions.add(
                        new WinCondition(
                                WinConditionType.valueOf(winCondition.winConditionName),
                                winCondition.timeBound,
                                winCondition.numberOfObjective)
                );
            }
            stage.setWinConditions(winConditions);

            stage.setLocked(dbStage.isLocked == 1);

            stage.setBrief(dbStage.summary);
            stage.setIndex(dbStage.idx);
            stage.setHighScore(dbStage.highScore);
            stage.setScore(dbStage.lastScore);
            stage.setDistributionType(DistributionType.UNIFORM);

            stages.add(stage);
        }

        List<PreferencesEntity> preferencesEntities = db.getPreferencesDAO().getPreferences();
        if (preferencesEntities.get(0).music == 1){
            preferences.setMusic(true);
        }else{
            preferences.setMusic(false);
        }

        if (preferencesEntities.get(0).sound == 1){
            preferences.setSound(true);
        }else{
            preferences.setSound(false);
        }
    }

    public void reinitializeDatabase(Context context){
        stages.clear();
        initializeDatabase(context);
    }

    public void commitDatabase(Context context){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                BriketDatabase db = BriketDatabase.getInstance(context);

                PreferencesEntity preferencesEntity = new PreferencesEntity();
                preferencesEntity.id = db.getPreferencesDAO().getPreferences().get(0).id;

                if (preferences.isMusic()){
                    preferencesEntity.music = 1;
                }else{
                    preferencesEntity.music = 0;
                }

                if (preferences.isSound()){
                    preferencesEntity.sound = 1;
                }else{
                    preferencesEntity.sound = 0;
                }

                db.getPreferencesDAO().update(preferencesEntity);
            }
        });
    }

    public void initializeAdMob(Context context){
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.i(TAG, "Mobile Ads has been initialized");
            }
        });
    }

    public void initializeGooglePlayServices(Context context){

    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setCurrentStage(int index) {
        this.currentStage = stages.get(index);
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    @Override
    public void onScoreEvent(int score) {
        gameStatistics.setScore(score);
    }

    @Override
    public void onComboEvent(int combo) {
        switch (combo){
            case 2:
                gameStatistics.setComboX2();
                break;
            case 3:
                gameStatistics.setComboX3();
                break;
            case 4:
                gameStatistics.setComboX4();
                break;
            default:
                Log.e(TAG, "Unknown Combo Event happened");
        }
    }

    @Override
    public void onTimeEvent(int time) {
        gameStatistics.setElapsedTime(time);
    }

    public class Music{

        public MediaPlayer game_play;

        private boolean isOn;

        public Music(){
            isOn = false;
        }

        public void load(Context context){
            game_play = MediaPlayer.create(context, R.raw.music_game_play);
            game_play.setLooping(true);

            if (BriketContext.getInstance().preferences.isMusic()){
                setOn();
            }else {
                setOff();
            }
        }

        public void setOn(){
            isOn = true;
        }

        public void setOff(){
            isOn = false;
        }

        public void play(MediaPlayer mediaPlayer){
            if (isOn) {
                mediaPlayer.start();
            }
        }

        public void pause(MediaPlayer mediaPlayer){
            mediaPlayer.pause();
        }

        public void resume(MediaPlayer mediaPlayer){
            if (isOn) mediaPlayer.start();
        }

        public void stop(MediaPlayer mediaPlayer){
            mediaPlayer.pause();
        }
    }

    public class Sound{

        private boolean isOn;
        private SoundPool soundPool;

        /* Sound IDs */
        public int lineup;

        public Sound(){

            this.isOn = false;

            this.soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .build();
        }

        public void load(Context context){
            lineup = this.soundPool.load(context, R.raw.music_line_destroy, 1);

            if (BriketContext.getInstance().preferences.isSound()){
                setOn();
            }else {
                setOff();
            }
        }

        public void setOn(){
            isOn = true;
        }

        public void setOff(){
            isOn = false;
        }

        public void play(int sound){
            if (isOn) soundPool.play(sound, 1f, 1f, 0, 0, 1f);
        }
    }
}
