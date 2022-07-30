package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Formation")
public class FormationEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String name;
    @ColumnInfo(defaultValue = "0") @NonNull
    public int toughness;
}
