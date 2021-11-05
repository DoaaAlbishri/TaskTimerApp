package com.example.tasktimerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tasktimerapp.database.Task
import com.example.tasktimerapp.database.TaskRepository
import com.example.tasktimerapp.database.TasksDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application : Application): AndroidViewModel(application) {

    private val repository: TaskRepository
    private val Task: LiveData<List<Task>>
    private val sum: LiveData<Int>
    private val time: Int

    init {
        val noteDao = TasksDatabase.getInstance(application).TaskDao()
        repository = TaskRepository(noteDao)
        Task = repository.getTasks
        sum = repository.getSum
        time = repository.getTime
    }

    fun getTasks(): LiveData<List<Task>>{
        return Task as LiveData<List<Task>>
    }

    fun getSum() : LiveData<Int> {
        return sum
    }
    fun getTime() : Int {
        return time
    }
    fun addTask(Titel: String, Description: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addTask(Task(0, Titel,Description,0,0))
        }
    }

    fun updateTask(Id: Int, Time: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateTask(Id, Time)
        }
    }

    fun updateTime(Id: Int, Time: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateTime(Id, Time)
        }
    }

    fun deleteTask(task:Task){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteTask(task)
        }
    }

}