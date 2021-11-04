package com.example.tasktimerapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.skydoves.elasticviews.ElasticButton

class AddTask : AppCompatActivity() {

    lateinit var textView2: TextView
    lateinit var saveBtn: ElasticButton
    lateinit var etName: EditText
    lateinit var etDescription: EditText
    lateinit var imageView: ImageView
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        textView2 = findViewById(R.id.textView2)
        saveBtn = findViewById(R.id.saveBtn)
        etName = findViewById(R.id.etName)
        etDescription = findViewById(R.id.etDescription)
        imageView = findViewById(R.id.imageView)

        saveBtn.setOnClickListener {
            save()
        }
    }

    fun save(){
        if(etName.text.isEmpty()||etDescription.text.isEmpty()){
            Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show()
        }else{
            val name = etName.text.toString()
            val description = etDescription.text.toString()
            //added them to DB
            myViewModel.addTask(name,description)
            Toast.makeText(this, "added Task successfully", Toast.LENGTH_SHORT).show()
            //show image view when added successfully
            imageView.isVisible = true
            etName.text.clear()
            etDescription.text.clear()
        }
        //hide image
        val countDownTimer = object : CountDownTimer(2000,1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                imageView.isVisible=false
            }
        }
        countDownTimer.start()
    }

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
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                return true

            }
            R.id.m2 -> {
                   val intent = Intent(applicationContext, ViewTask::class.java)
                   startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}