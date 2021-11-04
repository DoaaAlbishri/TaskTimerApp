package com.example.tasktimerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewTask : AppCompatActivity() {

    lateinit var recyclerView :RecyclerView
    lateinit var tasks_adapter : TasksAdapter
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        recyclerView = findViewById(R.id.recyclerView)

        myViewModel.getTasks().observe(this,{
            Tasks -> tasks_adapter.Update(Tasks)
        })


        tasks_adapter = TasksAdapter(this)
        recyclerView.adapter = tasks_adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    //Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.m2 -> {
                val intent = Intent(applicationContext, TaskTime::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}