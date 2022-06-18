package com.example.calendarapp.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import com.example.calendarapp.databinding.RvEventBinding
import com.example.calendarapp.models.EventData
import com.example.calendarapp.ui.MainActivity
import com.example.calendarapp.viewmodels.EventViewModel

class EventRvAdapter(private val items: ArrayList<EventViewModel>) :
    RecyclerView.Adapter<EventRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventRvAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvEventBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: RvEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewmodel: EventViewModel) {
            binding.viewmodel = viewmodel
            binding.sEventBtnRemove.setOnClickListener {
                items.remove(viewmodel)
                val dh = DataHandler()
                dh.removeEvent(it.context, viewmodel.id.value ?: -1)
                notifyDataSetChanged() // TODO
            }
            binding.root.setOnLongClickListener {
                val activity = it.context as MainActivity
                val data = EventData(
                    viewmodel.id.value ?: -1,
                    viewmodel.name.value ?: "",
                    viewmodel.desc.value ?: "",
                    viewmodel.startTime.value ?: "",
                    viewmodel.endTime.value ?: "",
                    viewmodel.date.value ?: "",
                    viewmodel.eventGroupName.value ?: "",
                    viewmodel.eventGroupImage.value
                )
                val bundle = bundleOf("eventData" to data)
                activity.navigate(R.id.formFragment, bundle)
                true
            }
        }
    }
}
