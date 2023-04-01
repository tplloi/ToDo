package com.roy93group.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loitp.annotation.LogTag
import com.loitp.core.adapter.BaseAdapter
import com.roy93group.R
import kotlinx.android.synthetic.main.view_row_item_header.view.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("HeaderAdapter")
class HeaderAdapter : BaseAdapter() {
    private var header: String = ""

    @SuppressLint("NotifyDataSetChanged")
    fun setData(header: String) {
        this.header = header
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {
            itemView.tvHeader.text = header
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_row_item_header, parent,
                false
            )
        )

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind()
        }
    }
}
