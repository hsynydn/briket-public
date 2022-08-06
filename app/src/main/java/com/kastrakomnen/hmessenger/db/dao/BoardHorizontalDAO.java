package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.BoardHorizontalEntity;

import java.util.List;

@Dao
public interface BoardHorizontalDAO {
    @Insert
    public void insert(BoardHorizontalEntity boardHorizontalEntity);

    @Update
    public void update(BoardHorizontalEntity boardHorizontalEntity);

    @Delete
    public void delete(BoardHorizontalEntity boardHorizontalEntity);

    @Query("SELECT * FROM BoardHorizontal")
    List<BoardHorizontalEntity> getBoardHorizontals();
}
