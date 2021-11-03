package com.example.tasktimerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskTime : AppCompatActivity() {
    lateinit var tvTotalForAll2: TextView
    lateinit var recyclerView22: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_time)
        tvTotalForAll2=findViewById(R.id.tvTotalForAll2)
        recyclerView22=findViewById(R.id.recyclerView22)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)
        val item2: MenuItem = menu!!.getItem(0)
        item2.setTitle("View Task")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                return true

            }
            R.id.m2 -> {
                val intent = Intent(this, ViewTask::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}