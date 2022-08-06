package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = StageEntity.class, parentColumns = "id", childColumns = "stageID"),
        @ForeignKey(entity = DistributionEntity.class, parentColumns = "id", childColumns = "distributionID"),
        @ForeignKey(entity = AgentEntity.class, parentColumns = "id", childColumns = "agentID")},
        tableName = "StageAgent"
)
public class StageAgentEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int stageID;
    @NonNull
    public int distributionID;
    @NonNull
    public int agentID;
}

