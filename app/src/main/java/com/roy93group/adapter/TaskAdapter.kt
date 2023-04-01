package com.roy93group.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loitp.annotation.LogTag
import com.loitp.core.adapter.BaseAdapter
import com.loitp.core.ext.getDateCurrentTimeZoneMls
import com.loitp.core.ext.setSafeOnClickListener
import com.roy93group.R
import com.roy93group.model.Task
import kotlinx.android.synthetic.main.view_row_item_task.view.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("TaskAdapter")
class TaskAdapter : BaseAdapter() {
    private val listTask = ArrayList<Task>()
    var onClickDeleteListener: ((Task) -> Unit)? = null
    var onClickCbCompleteListener: ((Task) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listTask: List<Task>) {
        this.listTask.clear()
        this.listTask.addAll(listTask)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task) {
            itemView.tvDate.text = task.id.toLong().getDateCurrentTimeZoneMls("dd-MM-yyyy HH:mm:ss")
            itemView.cbComplete.isChecked = task.isComplete
            itemView.tvTitle.text = task.title
            itemView.tvDescription.text = task.description

            itemView.cbComplete.setSafeOnClickListener {
                onClickCbCompleteListener?.invoke(task)
            }
            itemView.btDelete.setSafeOnClickListener {
                onClickDeleteListener?.invoke(task)
            }
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
