package com.kastrakomnen.hmessenger.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kastrakomnen.hmessenger.db.entity.DistributionEntity;

import java.util.List;

@Dao
public interface DistributionDAO {
    @Insert
    public void insert(DistributionEntity distributionEntity);

    @Update
    public void update(DistributionEntity distributionEntity);

    @Delete
    public void delete(DistributionEntity distributionEntity);

    @Query("SELECT * FROM Distribution")
    List<DistributionEntity> getDistributions();
}
