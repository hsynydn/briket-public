package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.StageAgentEntity;

import java.util.List;

@Dao
public interface StageAgentDAO {
    @Insert
    public void insert(StageAgentEntity stageAgentEntity);

    @Update
    public void update(StageAgentEntity stageAgentEntity);

    @Delete
    public void delete(StageAgentEntity stageAgentEntity);

    @Query("SELECT * FROM StageAgent")
    List<StageAgentEntity> getStageAgent();
}
