package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Preferences")
public class Preferences {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(defaultValue = "1") @NonNull
    public int music;
    @ColumnInfo(defaultValue = "1") @NonNull
    public int sound;
}
