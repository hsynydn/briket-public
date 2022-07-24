package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.Stage;

import java.util.List;

@Dao
public interface StageDAO {

    @Insert
    public void insert(Stage stage);

    @Update
    public void update(Stage stage);

    @Delete
    public void delete(Stage stage);

    @Query("SELECT * FROM Stage")
    List<Stage> getStages();
}
