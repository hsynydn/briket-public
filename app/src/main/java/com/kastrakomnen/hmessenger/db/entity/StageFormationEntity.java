package com.kastrakomnen.hmessenger.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = StageEntity.class, parentColumns = "id", childColumns = "stageID"),
        @ForeignKey(entity = FormationEntity.class, parentColumns = "id", childColumns = "formationID")},
        tableName = "StageFormation"
)
public class StageFormationEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public int stageID;
    @NonNull
    public int formationID;
}
