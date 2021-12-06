package com.loitp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annotation.LogTag
import com.core.adapter.BaseAdapter
import com.core.base.BaseApplication
import com.core.utilities.LDateUtil
import com.loitp.R
import com.loitp.model.Task
import kotlinx.android.synthetic.main.view_row_item_task.view.*

@LogTag("TaskAdapter")
class TaskAdapter : BaseAdapter() {
    private val listTask = ArrayList<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listTask: List<Task>) {
        this.listTask.clear()
        this.listTask.addAll(listTask)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task) {
            logD(">>> $bindingAdapterPosition " + BaseApplication.gson.toJson(task))
            itemView.tvDate.text = LDateUtil.getDateCurrentTimeZoneMls(task.id.toLong(), "dd-MM-yyyy HH:mm:ss")
            itemView.cbComplete.isChecked = task.isComplete
            itemView.tvTitle.text = task.title
            itemView.tvDescription.text = task.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_row_item_task, parent,
                false
            )
        )

    override fun getItemCount(): Int = listTask.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TaskViewHolder) {
            holder.bind(listTask[position])
        }
    }
}
