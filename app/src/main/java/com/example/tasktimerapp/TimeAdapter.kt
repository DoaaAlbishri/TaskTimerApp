package com.example.tasktimerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.database.Task
import kotlinx.android.synthetic.main.item_row2.view.*

class TimeAdapter(val activity: TaskTime) : RecyclerView.Adapter<TimeAdapter.ItemViewHolder>() {

    private var Tasks = emptyList<Task>()
    private val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row2,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = Tasks[position]
        holder.itemView.apply {
            tvNameAll.text = task.title
            tvTimerAll.text = task.totalTime.toString() +" sec"
        }
    }

    override fun getItemCount() = Tasks.count()

    fun Update(tasks : List<Task>){
        this.Tasks = tasks
        myViewModel.getSum()
        notifyDataSetChanged()
    }

}