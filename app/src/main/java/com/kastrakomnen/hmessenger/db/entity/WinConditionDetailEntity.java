package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "WinConditionDetail",
        foreignKeys = {@ForeignKey(entity = WinConditionEntity.class, parentColumns = "id", childColumns = "winConditionID")}
)
public class WinConditionDetailEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int winConditionID;

    @ColumnInfo(defaultValue = "600") @NonNull
    public int timeBound;

    @ColumnInfo(defaultValue = "0") @NonNull
    public int numberOfObjective;

}
