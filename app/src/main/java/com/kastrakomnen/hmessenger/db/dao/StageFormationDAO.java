package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.StageFormationEntity;

import java.util.List;

@Dao
public interface StageFormationDAO {
    @Insert
    public void insert(StageFormationEntity stageFormation);

    @Update
    public void update(StageFormationEntity stageFormation);

    @Delete
    public void delete(StageFormationEntity stageFormation);

    @Query("SELECT * FROM StageFormation")
    List<StageFormationEntity> getStageFormations();
}
