package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.FormationEntity;

import java.util.List;

@Dao
public interface FormationDAO {

    @Insert
    public void insert(FormationEntity formation);

    @Update
    public void update(FormationEntity formation);

    @Delete
    public void delete(FormationEntity formation);

    @Query("SELECT * FROM Formation")
    List<FormationEntity> getFormations();
}
