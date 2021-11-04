package com.example.tasktimerapp

import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.androchef.happytimer.countdowntimer.HappyTimer
import com.example.tasktimerapp.database.Task
import kotlinx.android.synthetic.main.item_row.view.*
import java.text.SimpleDateFormat

class TasksAdapter(val activity: ViewTask, val PH: SharedPreferences): RecyclerView.Adapter<TasksAdapter.ItemViewHolder>() {
    private var Tasks = emptyList<Task>()
    private var Init_Timer = 10000
    var ACTIVE = false
    var TIME = 0
    var CId = 0
    var timer = HappyTimer(Init_Timer)
    var num = 0
    var t = 0
    private val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = Tasks[position]
        holder.itemView.apply {
            tvName.text = task.title
            tvDescription.text = task.description
            num = PH.getInt("Id",num)
            if(num == task.id){
                t = PH.getInt("Time",t)
                tvTimer.text = "Timer : ${SimpleDateFormat("mm:ss").format(t*1000)}"
                t= 0
                num = 0
            }
            cv.setOnClickListener {
                if(!ACTIVE){
                    CId = task.id
                    timer.start()
                    timer.setOnTickListener(object :HappyTimer.OnTickListener{
                        //OnTick
                        override fun onTick(completedSeconds: Int, remainingSeconds: Int) {
                            tvTimer.text = "Timer : ${SimpleDateFormat("mm:ss").format(completedSeconds*1000)}"
                            TIME = remainingSeconds
                        }

                        override fun onTimeUp(){}
                    })
                    ACTIVE = true
                }else{
                    TIME = Init_Timer - TIME
                    if(CId != task.id){
                        timer.stop()
                        myViewModel.updateTask(CId, task.totalTime+TIME)
                        save(CId,TIME)
                        timer = HappyTimer(Init_Timer)
                        timer.start()
                        timer.setOnTickListener(object :HappyTimer.OnTickListener{
                            override fun onTick(completedSeconds: Int, remainingSeconds: Int) {
                                tvTimer.text = "Timer : ${SimpleDateFormat("mm:ss").format(completedSeconds*1000)}"
                                TIME = remainingSeconds
                            }
                            override fun onTimeUp(){}
                        })
                        CId = task.id
                        ACTIVE = true
                    }else{
                        timer.stop()
                        timer = HappyTimer(Init_Timer)
                        myViewModel.updateTask(CId, task.totalTime+TIME)
                        save(CId,TIME)
                        ACTIVE = false
                        TIME = 0
                    }
                }

            }
        }
    }

    override fun getItemCount() = Tasks.count()

    fun Update(tasks : List<Task>){
        this.Tasks = tasks
        notifyDataSetChanged()
    }
    fun save(id: Int, time: Int) {
        with(PH.edit()){
            putInt("Id",id)
            putInt("Time",time)
            apply()
        }
        this.CId = 0
        num = 0
    }
}
