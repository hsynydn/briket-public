package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.BotBehaviourEntity;

import java.util.List;

@Dao
public interface BotBehaviourDAO {
    @Insert
    public void insert(BotBehaviourEntity botBehaviour);

    @Update
    public void update(BotBehaviourEntity botBehaviour);

    @Delete
    public void delete(BotBehaviourEntity botBehaviour);

    @Query("SELECT * FROM BotBehaviour")
    List<BotBehaviourEntity> getBotBehaviours();
}
