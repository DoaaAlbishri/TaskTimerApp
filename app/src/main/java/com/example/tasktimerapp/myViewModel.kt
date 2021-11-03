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

class myViewModel(application : Application): AndroidViewModel(application) {

    private val repository: TaskRepository
    private val Task: LiveData<List<Task>>
    init {
        val noteDao = TasksDatabase.getInstance(application).TaskDao()
        repository = TaskRepository(noteDao)
        Task = repository.getTasks
    }

    fun getTasks(): LiveData<List<Task>>{
        return Task as LiveData<List<Task>>
    }

    fun addTask(Titel: String, Description: String,Time: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addTask(Task(0, Titel,Description,Time))
        }
    }

}