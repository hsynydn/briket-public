package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.BotBehaviour;

import java.util.List;

@Dao
public interface BotBehaviourDAO {
    @Insert
    public void insert(BotBehaviour botBehaviour);

    @Update
    public void update(BotBehaviour botBehaviour);

    @Delete
    public void delete(BotBehaviour botBehaviour);

    @Query("SELECT * FROM BotBehaviour")
    List<BotBehaviour> getBotBehaviours();
}
