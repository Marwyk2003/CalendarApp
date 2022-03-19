package com.example.calendarapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(private val mEvents: ArrayList<RvEvent>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.sEvent_tv_name)
        val tvStartTime: TextView = itemView.findViewById(R.id.sEvent_tv_startTime)
        val tvEndTime: TextView = itemView.findViewById(R.id.sEvent_tv_endTime)
        val btnRemove: Button = itemView.findViewById(R.id.sEvent_btn_remove)
        val view = itemView
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
        holder.btnRemove.setOnClickListener {
            mEvents.remove(event)
            val dh = DataHandler()
            dh.removeEvent(it.context, event.id)
            notifyDataSetChanged()
        }
        holder.view.setOnLongClickListener {
            val activity = it.context as MainActivity
            val eventData = DataHandler().readEvent(activity, event.id)
            activity.navigateToForm(eventData)
            true
        }
        holder.tvName.text = event.name
        holder.tvStartTime.text = event.startTime
        holder.tvEndTime.text = event.endTime
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }
}
