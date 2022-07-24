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
import com.kastrakomnen.hmessenger.db.entity.BotBehaviour;
import com.kastrakomnen.hmessenger.db.entity.Formation;
import com.kastrakomnen.hmessenger.db.entity.Preferences;
import com.kastrakomnen.hmessenger.db.entity.Stage;
import com.kastrakomnen.hmessenger.db.entity.StageFormation;

@Database(entities = {BotBehaviour.class, Formation.class, Preferences.class, Stage.class, StageFormation.class}, version = 19, exportSchema = true)
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
