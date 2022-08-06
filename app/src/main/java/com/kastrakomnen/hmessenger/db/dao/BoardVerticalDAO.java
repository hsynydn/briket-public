package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.BoardVerticalEntity;

import java.util.List;

@Dao
public interface BoardVerticalDAO {
    @Insert
    public void insert(BoardVerticalEntity boardVerticalEntity);

    @Update
    public void update(BoardVerticalEntity boardVerticalEntity);

    @Delete
    public void delete(BoardVerticalEntity boardVerticalEntity);

    @Query("SELECT * FROM BoardVertical")
    List<BoardVerticalEntity> getBoardVerticals();
}
