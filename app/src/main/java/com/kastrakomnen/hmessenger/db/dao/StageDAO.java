package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.FormationEntity;
import com.kastrakomnen.hmessenger.db.entity.StageEntity;

import java.util.List;

@Dao
public interface StageDAO {

    @Insert
    public void insert(StageEntity stage);

    @Update
    public void update(StageEntity stage);

    @Delete
    public void delete(StageEntity stage);

    @Query("SELECT * FROM Stage")
    List<StageEntity> getStages();

    @Query("SELECT Formation.* FROM StageFormation INNER JOIN Formation On StageFormation.stageID==:stageID AND StageFormation.formationID=Formation.id")
    List<FormationEntity> getFormations(int stageID);
}