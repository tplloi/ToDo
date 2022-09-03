package com.roy93group.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.adapter.BaseAdapter
import com.roy93group.R
import kotlinx.android.synthetic.main.view_row_item_header.view.*

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
