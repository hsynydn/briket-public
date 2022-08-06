package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Board")
public class BoardEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public int height;
    @NonNull
    public int width;
}
