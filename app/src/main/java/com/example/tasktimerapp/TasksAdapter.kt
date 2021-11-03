package com.example.tasktimerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimerapp.database.Task
import kotlinx.android.synthetic.main.item_row.view.*

class TasksAdapter(val activity: ViewTask): RecyclerView.Adapter<TasksAdapter.ItemViewHolder>() {

    private var Tasks = emptyList<Task>()

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
        val Task = Tasks[position]
        holder.itemView.apply {

            tvName.text = Task.title
            tvDescription.text = Task.description
            tvTimer.text = Task.totalTime.toString()

        }
    }

    override fun getItemCount() = Tasks.count()

    fun Update(tasks : List<Task>){
        this.Tasks = tasks
        notifyDataSetChanged()
    }

}