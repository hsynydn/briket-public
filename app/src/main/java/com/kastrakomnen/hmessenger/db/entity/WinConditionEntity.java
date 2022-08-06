package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WinCondition")
public class WinConditionEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;
}

