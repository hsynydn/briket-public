package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.Formation;

import java.util.List;

@Dao
public interface FormationDAO {

    @Insert
    public void insert(Formation formation);

    @Update
    public void update(Formation formation);

    @Delete
    public void delete(Formation formation);

    @Query("SELECT * FROM Formation")
    List<Formation> getFormations();
}
