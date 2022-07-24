package com.kastrakomnen.hmessenger.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Stage.class, parentColumns = "id", childColumns = "stageID"),
        @ForeignKey(entity = Formation.class, parentColumns = "id", childColumns = "formationID")},
        tableName = "StageFormation"
)
public class StageFormation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public int stageID;
    @NonNull
    public int formationID;
}
