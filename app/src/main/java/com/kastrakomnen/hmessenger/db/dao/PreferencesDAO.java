package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.Preferences;

import java.util.List;

@Dao
public interface PreferencesDAO {

    @Insert
    public void insert(Preferences preferences);

    @Update
    public void update(Preferences preferences);

    @Delete
    public void delete(Preferences preferences);

    @Query("SELECT * FROM Preferences")
    List<Preferences> getPreferences();
}
