package com.example.tasktimerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class],version = 1,exportSchema = false)
abstract class TasksDatabase: RoomDatabase() {

    companion object{
        var instance: TasksDatabase?=null;
        fun getInstance(ctx: Context): TasksDatabase
        {
            if(instance !=null)
            {
                return  instance as TasksDatabase;
            }
            instance = Room.databaseBuilder(ctx, TasksDatabase::class.java,"Tasks_DB").run { allowMainThreadQueries() }.build();
            return instance as TasksDatabase;
        }
    }
    abstract fun TaskDao(): TaskDao;
}