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
import com.kastrakomnen.hmessenger.db.entity.intermediate.AgentIntermediateData;
import com.kastrakomnen.hmessenger.db.entity.intermediate.WinConditionIntermediateData;
import com.kastrakomnen.hmessenger.model.set.Agent;
import com.kastrakomnen.hmessenger.model.set.BrickType;
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

    public Music music;
    public Sound sound;

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

            /* Set Up Agents */
            ArrayList<Agent> agents = new ArrayList<>();
            for (AgentIntermediateData agentIntermediateData: db.getStageDAO().getAgents(dbStage.id)) {
                agents.add(
                        new Agent(
                                BrickType.valueOf(agentIntermediateData.agentType),
                                DistributionType.valueOf(agentIntermediateData.distributionType)
                        )
                );
            }

            stage.setWinConditions(winConditions);
            stage.setId(dbStage.id);
            stage.setLocked(dbStage.isLocked == 1);
            stage.setCompleted(dbStage.isCompleted == 1);
            stage.setBrief(dbStage.summary);
            stage.setIndex(dbStage.idx);
            stage.setHighScore(dbStage.highScore);
            stage.setScore(dbStage.lastScore);
            stage.setDistributionType(DistributionType.UNIFORM);
            stage.setAgents(agents);

            stages.add(stage);
        }

        List<PreferencesEntity> preferencesEntities = db.getPreferencesDAO().getPreferences();
        preferences.setMusic(preferencesEntities.get(0).music == 1);
        preferences.setSound(preferencesEntities.get(0).sound == 1);
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

                preferencesEntity.music = preferences.isMusic() ? 1 : 0;
                preferencesEntity.sound = preferences.isSound() ? 1 : 0;

                db.getPreferencesDAO().update(preferencesEntity);

                StageEntity stageEntity = db.getStageDAO().getStageByID(currentStage.getId());
                stageEntity.highScore = currentStage.getHighScore();
                stageEntity.isCompleted = currentStage.isCompleted() ? 1 : 0;
                stageEntity.isLocked = currentStage.isLocked() ? 1 : 0;

                db.getStageDAO().update(stageEntity);

                for (int i = 0; i < stages.size() - 1; i++) {
                    if (stages.get(i).isCompleted() && stages.get(i + 1).isLocked()){
                        stages.get(i + 1).setLocked(false);

                        StageEntity tmp = db.getStageDAO().getStageByID(stages.get(i + 1).getId());
                        tmp.isLocked = stages.get(i + 1).isLocked() ? 1 : 0;

                        db.getStageDAO().update(tmp);
                    }
                }
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
