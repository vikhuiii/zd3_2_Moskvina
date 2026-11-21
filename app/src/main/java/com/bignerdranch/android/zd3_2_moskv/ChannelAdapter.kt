package com.bignerdranch.android.zd3_2_moskv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChannelAdapter(
    private val channels: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    inner class ChannelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvChannelName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channels[position]
        holder.tvName.text = channel

        holder.itemView.setOnClickListener { onItemClick(channel) }

        holder.itemView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) v.setBackgroundColor(v.context.getColor(R.color.aquamarin))
            else v.setBackgroundColor(v.context.getColor(R.color.gray))
        }
    }

    override fun getItemCount() = channels.size
}