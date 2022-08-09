package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "StageWithWinConditionDetails",
        foreignKeys = {
                @ForeignKey(entity = StageEntity.class, parentColumns = "id", childColumns = "stageID"),
                @ForeignKey(entity = WinConditionDetailEntity.class, parentColumns = "id", childColumns = "winConditionDetailID")
        }
)
public class StageWithWinConditionDetailsEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int stageID;
    @NonNull
    public int winConditionDetailID;
}
