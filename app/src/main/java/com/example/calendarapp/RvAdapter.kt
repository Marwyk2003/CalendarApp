package com.example.calendarapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(private val mEvents: List<RvEvent>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val startTimeTextView: TextView = itemView.findViewById(R.id.startTimeTextView)
        val endTimeTextView: TextView = itemView.findViewById(R.id.endTimeTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RvAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val eventView = inflater.inflate(R.layout.event_short, parent, false)
        return ViewHolder(eventView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = mEvents[position]
        holder.nameTextView.text = event.name
        holder.startTimeTextView.text = event.timeStart
        holder.endTimeTextView.text = event.timeEnd
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }
}
