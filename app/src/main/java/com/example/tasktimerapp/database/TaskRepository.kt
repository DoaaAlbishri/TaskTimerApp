package com.example.tasktimerapp.database

import androidx.lifecycle.LiveData

class TaskRepository(private val TaskDao: TaskDao) {

    val getTasks: LiveData<List<Task>> = TaskDao.getTasks()

    suspend fun addTask(task: Task){
        TaskDao.insertTask(task)
    }

    suspend fun updateTask(id: Int, totalTime:Int){
        TaskDao.updateTask(id, totalTime)
    }

    suspend fun updateTime(id: Int, time:Int){
        TaskDao.updateTime(id, time)
    }

    val getSum: LiveData<Int> = TaskDao.getSum()
    val getTime: Int = TaskDao.getTime()

    suspend fun deleteTask(task: Task){
        TaskDao.deleteTask(task)
    }
}