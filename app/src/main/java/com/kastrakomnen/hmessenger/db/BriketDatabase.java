package com.kastrakomnen.hmessenger.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kastrakomnen.hmessenger.db.dao.BotBehaviourDAO;
import com.kastrakomnen.hmessenger.db.dao.FormationDAO;
import com.kastrakomnen.hmessenger.db.dao.PreferencesDAO;
import com.kastrakomnen.hmessenger.db.dao.StageDAO;
import com.kastrakomnen.hmessenger.db.dao.StageFormationDAO;
import com.kastrakomnen.hmessenger.db.entity.BotBehaviourEntity;
import com.kastrakomnen.hmessenger.db.entity.FormationEntity;
import com.kastrakomnen.hmessenger.db.entity.PreferencesEntity;
import com.kastrakomnen.hmessenger.db.entity.StageEntity;
import com.kastrakomnen.hmessenger.db.entity.StageFormationEntity;

@Database(entities = {BotBehaviourEntity.class, FormationEntity.class, PreferencesEntity.class, StageEntity.class, StageFormationEntity.class}, version = 19, exportSchema = true)
public abstract class BriketDatabase extends RoomDatabase {

    private static volatile BriketDatabase instance;

    public static synchronized BriketDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static BriketDatabase create(final Context context) {
        return Room
                .databaseBuilder(context, BriketDatabase.class, "briket")
                .createFromAsset("database/briket.db")
                .build();
    }

    public abstract BotBehaviourDAO getBotBehaviourDAO();
    public abstract PreferencesDAO getPreferencesDAO();
    public abstract StageDAO getStageDAO();
    public abstract FormationDAO getFormationDAO();
    public abstract StageFormationDAO getStageFormationDAO();
}
