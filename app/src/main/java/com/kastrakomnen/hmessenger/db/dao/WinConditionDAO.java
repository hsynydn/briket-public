package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.WinConditionEntity;

import java.util.List;

@Dao
public interface WinConditionDAO {
    @Insert
    public void insert(WinConditionEntity winConditionEntity);

    @Update
    public void update(WinConditionEntity winConditionEntity);

    @Delete
    public void delete(WinConditionEntity winConditionEntity);

    @Query("SELECT * FROM WinCondition")
    List<WinConditionEntity> getWinConditions();
}
