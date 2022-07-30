package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BotBehaviour")
public class BotBehaviourEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public int cmdPeriodStart;
    @NonNull
    public int cmdPeriodEnd;
    @NonNull
    public int updatePeriod;
    @NonNull
    public int increase;
}
