package com.example.tasktimerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.skydoves.elasticviews.ElasticButton

class MainActivity : AppCompatActivity() {

    lateinit var addBtn: ElasticButton
    lateinit var viewBtn: ElasticButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtn = findViewById(R.id.addBtn)
        viewBtn = findViewById(R.id.viewBtn)

        addBtn.setOnClickListener {
            val intent = Intent(applicationContext,AddTask::class.java)
            startActivity(intent)
        }

        viewBtn.setOnClickListener {
            val intent = Intent(applicationContext,ViewTask::class.java)
            startActivity(intent)
        }
    }
}