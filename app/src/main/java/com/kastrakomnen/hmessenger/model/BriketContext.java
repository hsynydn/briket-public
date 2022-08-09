package com.kastrakomnen.hmessenger.model;

import android.content.Context;
import android.util.Log;

import androidx.core.app.NavUtils;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
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

    private BriketContext(){
        stages = new ArrayList<>();
        preferences = new Preferences();
        gameStatistics = new GameStatistics();
    }

    public static BriketContext getInstance() {
        if (instance == null){
            instance = new BriketContext();
        }

        return instance;
    }

    public void initializeDatabase(Context context){

        BriketDatabase db = BriketDatabase.getInstance(context);

        for (StageEntity dbStage : db.getStageDAO().getStages()) {

            Stage stage = new Stage();

            /* Set Up Formation Types */
            ArrayList<FormationType> formationTypes = new ArrayList<>();
            for (FormationEntity dbFormation : db.getStageDAO().getFormations(dbStage.id)) {
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
            stage.setWinConditions(null);

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
}
