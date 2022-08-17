package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.FormationEntity;
import com.kastrakomnen.hmessenger.db.entity.StageEntity;
import com.kastrakomnen.hmessenger.db.entity.intermediate.AgentIntermediateData;
import com.kastrakomnen.hmessenger.db.entity.intermediate.WinConditionIntermediateData;

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
    public List<StageEntity> getStages();

    @Query("SELECT * FROM Stage WHERE id==:stageID")
    public StageEntity getStageByID(int stageID);

    @Query("SELECT Formation.* FROM StageFormation INNER JOIN Formation On StageFormation.stageID==:stageID AND StageFormation.formationID=Formation.id")
    public List<FormationEntity> getFormations(int stageID);

    @Query("SELECT WinCondition.name AS winConditionName, WinConditionDetail.timeBound, WinConditionDetail.numberOfObjective FROM StageWithWinConditionDetails, WinConditionDetail, WinCondition WHERE StageWithWinConditionDetails.stageID=:stageID AND WinConditionDetail.winConditionID=WinCondition.id")
    public List<WinConditionIntermediateData> getWinConditionDetails(int stageID);

    @Query("SELECT Distribution.name AS distributionType, Agent.name AS agentType FROM StageAgent INNER JOIN Agent ON StageAgent.stageID=:stageID AND StageAgent.agentID==Agent.id INNER JOIN Distribution ON StageAgent.distributionID==Distribution.id")
    public List<AgentIntermediateData> getAgents(int stageID);
}
