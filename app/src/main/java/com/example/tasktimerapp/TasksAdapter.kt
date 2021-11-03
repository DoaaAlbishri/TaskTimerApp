package com.example.tasktimerapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.database.Task
import kotlinx.android.synthetic.main.item_row.view.*

class TasksAdapter(val activity: ViewTask): RecyclerView.Adapter<TasksAdapter.ItemViewHolder>() {

    private var Tasks = emptyList<Task>()
    private var timer = timer(1000)
    var ACTIVE = false
    var TIME = 0
    var CId = 0
    private val myViewModel by lazy { ViewModelProvider(activity).get(myViewModel::class.java) }

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
            //tvTimer.text = Task.totalTime.toString()
            cv.setOnClickListener {
                if(!ACTIVE){
                    CId = task.id
                    timer.start()
                    timer.setOnTickListener(object :timer.OnTickListener{
                        //OnTick
                        override fun onTick(completedSeconds: Int, remainingSeconds: Int) {
                            tvTimer.text = "Timer : $completedSeconds"
                            TIME = remainingSeconds
                        }

                        override fun onTimeUp(){}
                    })
                    ACTIVE = true
                }else{
                    timer.stop()
                    TIME = 1000 - TIME
                    myViewModel.updateTask(CId, task.totalTime+TIME)
                    ACTIVE = false
                    TIME = 0
                    timer = timer(1000)
                }

            }
        }
    }

    override fun getItemCount() = Tasks.count()

    fun Update(tasks : List<Task>){
        this.Tasks = tasks
        notifyDataSetChanged()
    }
}
