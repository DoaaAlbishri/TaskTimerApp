package com.example.tasktimerapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Task (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "TotalTime") val totalTime: String,
)