package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.StageFormation;

import java.util.List;

@Dao
public interface StageFormationDAO {
    @Insert
    public void insert(StageFormation stageFormation);

    @Update
    public void update(StageFormation stageFormation);

    @Delete
    public void delete(StageFormation stageFormation);

    @Query("SELECT * FROM StageFormation")
    List<StageFormation> getStageFormations();
}
