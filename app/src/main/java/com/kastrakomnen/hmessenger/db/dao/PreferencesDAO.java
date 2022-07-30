package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.PreferencesEntity;

import java.util.List;

@Dao
public interface PreferencesDAO {

    @Insert
    public void insert(PreferencesEntity preferences);

    @Update
    public void update(PreferencesEntity preferences);

    @Delete
    public void delete(PreferencesEntity preferences);

    @Query("SELECT * FROM Preferences")
    List<PreferencesEntity> getPreferences();
}
