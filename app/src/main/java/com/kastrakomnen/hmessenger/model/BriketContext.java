package com.kastrakomnen.hmessenger.model;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kastrakomnen.hmessenger.db.BriketDatabase;
import com.kastrakomnen.hmessenger.db.entity.FormationEntity;
import com.kastrakomnen.hmessenger.db.entity.PreferencesEntity;
import com.kastrakomnen.hmessenger.db.entity.StageEntity;

import java.util.ArrayList;
import java.util.List;

public class BriketContext {

    private static final String TAG = "{BriketContext}";

    private ArrayList<Stage> stages = new ArrayList<>();
    private Preferences preferences = new Preferences();
    private static BriketContext instance;

    private Stage currentStage;

    private BriketContext(){}

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
            ArrayList<FormationType> formationTypes = new ArrayList<>();

            for (FormationEntity dbFormation : db.getStageDAO().getFormations(dbStage.id)) {
                formationTypes.add(FormationType.valueOf(dbFormation.name));
            }

            if (dbStage.isLocked == 1){
                stage.setLocked(true);
            }else{
                stage.setLocked(false);
            }

            stage.setBrief(dbStage.summary);
            stage.setIndex(dbStage.idx);
            stage.setHighScore(dbStage.highScore);
            stage.setScore(dbStage.lastScore);

            stage.setFormationTypes(formationTypes);
            stage.setDistributionType(DistributionType.UNIFORM);
            stage.setWinCondition(WinCondition.NUMBER_OF_SEQUENCE);

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
}
