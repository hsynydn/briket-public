package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.BoardEntity;

import java.util.List;

@Dao
public interface BoardDAO {
    @Insert
    public void insert(BoardEntity boardEntity);

    @Update
    public void update(BoardEntity boardEntity);

    @Delete
    public void delete(BoardEntity boardEntity);

    @Query("SELECT * FROM Board")
    List<BoardEntity> getBoards();
}
