package com.example.tasktimerapp

//update adapter doaa
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.androchef.happytimer.countdowntimer.HappyTimer
import com.example.tasktimerapp.database.Task
import kotlinx.android.synthetic.main.item_row.view.*
import java.text.SimpleDateFormat
class TasksAdapter(val activity: ViewTask): RecyclerView.Adapter<TasksAdapter.ItemViewHolder>() {
    private var Tasks = emptyList<Task>()
    private var Init_Timer = 100000
    var ACTIVE = false
    var TIME = 0
    var CId = 0
    var timer = HappyTimer(Init_Timer)
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
            tvTimer.text= "Timer : ${SimpleDateFormat("mm:ss").format(task.time*1000)}"
            cv.setOnClickListener {
                if(!ACTIVE){
                    CId = task.id
                    timer.resume()
                        timer.setOnTickListener(object :HappyTimer.OnTickListener{
                        //OnTick
                        override fun onTick(completedSeconds: Int, remainingSeconds: Int) {
                            var finalTime = task.time+completedSeconds
                            val countDownTimer = object : CountDownTimer(1000,500) {
                                override fun onTick(p0: Long) {
                                }
                                override fun onFinish() {
                                    tvTimer.text = "Timer : ${SimpleDateFormat("mm:ss").format(finalTime*1000)}"
                                }
                            }
                            countDownTimer.start()
                            //println(completedSeconds)
                            TIME = remainingSeconds - task.time
                            myViewModel.updateTime(CId,finalTime)
                        }
                        override fun onTimeUp(){
                        }
                    })
                    ACTIVE = true
                }else{
                    TIME = Init_Timer - TIME
                    if(CId != task.id){
                        timer.pause()
                        myViewModel.updateTime(CId,TIME)
                        myViewModel.updateTask(CId, TIME)
                        timer = HappyTimer(Init_Timer)
                        timer.resume()
                        timer.setOnTickListener(object :HappyTimer.OnTickListener{
                            override fun onTick(completedSeconds: Int, remainingSeconds: Int) {
                                var finalTime = task.time+completedSeconds
                                val countDownTimer = object : CountDownTimer(1000,500) {
                                    override fun onTick(p0: Long) {
                                    }
                                    override fun onFinish() {
                                        tvTimer.text = "Timer : ${SimpleDateFormat("mm:ss").format(finalTime*1000)}"
                                    }
                                }
                                countDownTimer.start()
                                TIME = remainingSeconds - task.time
                            }
                            override fun onTimeUp(){}
                        })
                        CId = task.id
                        ACTIVE = true
                    }else{
                        timer.pause()
                        timer = HappyTimer(Init_Timer)
                        myViewModel.updateTime(CId,TIME)
                        myViewModel.updateTask(CId, TIME)
                        ACTIVE = false
                        TIME = 0
                    }
                }
            }
            deleteBtn.setOnClickListener {
                myViewModel.deleteTask(task)
            }
        }
    }

    override fun getItemCount() = Tasks.count()

    fun Update(tasks : List<Task>){
        this.Tasks = tasks
        notifyDataSetChanged()
    }
}
