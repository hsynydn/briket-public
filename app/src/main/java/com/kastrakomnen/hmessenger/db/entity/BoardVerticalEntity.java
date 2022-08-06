package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "BoardVertical",
        foreignKeys = {
                @ForeignKey(entity = StageEntity.class, parentColumns = "id", childColumns = "stageID"),
                @ForeignKey(entity = AgentEntity.class, parentColumns = "id", childColumns = "agentID")
        }
)
public class BoardVerticalEntity {
    @PrimaryKey(autoGenerate = true)
    public int stageID;
    @NonNull
    public int index;
    @NonNull
    public int agentID;
}

