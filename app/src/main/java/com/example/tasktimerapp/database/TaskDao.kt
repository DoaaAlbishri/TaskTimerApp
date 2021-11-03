package com.example.tasktimerapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks ORDER BY Title DESC")
    fun getTasks(): LiveData<List<Task>>

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("UPDATE Tasks SET Title=:t WHERE id=:id")
    fun updateTask(id:Int,t:String)

}