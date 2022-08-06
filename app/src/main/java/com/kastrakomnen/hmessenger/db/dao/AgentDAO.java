package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.AgentEntity;

import java.util.List;

@Dao
public interface AgentDAO {

    @Insert
    public void insert(AgentEntity agentEntity);

    @Update
    public void update(AgentEntity agentEntity);

    @Delete
    public void delete(AgentEntity agentEntity);

    @Query("SELECT * FROM Agent")
    List<AgentEntity> getAgents();
}
