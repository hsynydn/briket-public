package com.kastrakomnen.hmessenger.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = BotBehaviour.class,
        parentColumns = "id",
        childColumns = "botBehaviour"),
        tableName = "Stage"
)
public class Stage {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(defaultValue = "0")
    public int id;

    @ColumnInfo(defaultValue = "0") @NonNull
    public int idx;

    public String name;

    @ColumnInfo(defaultValue = "0") @NonNull
    public int highScore;

    @ColumnInfo(defaultValue = "0") @NonNull
    public int lastScore;

    @ColumnInfo(defaultValue = "0") @NonNull
    public int stars;

    @ColumnInfo(defaultValue = "1") @NonNull
    public int isLocked;
    public String summary;

    public int botBehaviour;
}
