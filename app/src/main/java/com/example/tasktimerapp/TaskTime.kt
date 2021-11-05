package com.example.tasktimerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import java.text.SimpleDateFormat

class TaskTime : AppCompatActivity() {

    lateinit var tvTotalForAll2: TextView
    lateinit var recyclerView22: RecyclerView
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    lateinit var time_adapter : TimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_time)
        setTitle("Task Summary")
        tvTotalForAll2=findViewById(R.id.tvTotalForAll2)
        recyclerView22=findViewById(R.id.recyclerView22)

        myViewModel.getTasks().observe(this,{
                Tasks -> time_adapter.Update(Tasks)
        })
        myViewModel.getSum().observe(this,{
            sum -> if(sum!=null) setText(sum) else setText(0)
        })

        time_adapter = TimeAdapter(this)
        recyclerView22.adapter = time_adapter
        recyclerView22.layoutManager = LinearLayoutManager(this)

    }
    fun setText(sum: Int){
        //tvTotalForAll2.text= "Total Time For All Task : ${SimpleDateFormat("mm:ss").format(sum*1000)}"
        tvTotalForAll2.text = "Total Time For All Task : $sum sec"
    }

    //Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)
        val item2: MenuItem = menu!!.getItem(1)
        item2.setTitle("View Task")
        item2.setIcon(R.drawable.ic_baseline_calendar_view_day_24)
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