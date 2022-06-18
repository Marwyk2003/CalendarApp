package com.example.calendarapp.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import com.example.calendarapp.databinding.RvEventGroupBinding
import com.example.calendarapp.models.EventGroupData
import com.example.calendarapp.ui.MainActivity
import com.example.calendarapp.viewmodels.EventGroupViewModel

class EventGroupRvAdapter(private val items: ArrayList<EventGroupViewModel>) :
    RecyclerView.Adapter<EventGroupRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvEventGroupBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: RvEventGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewmodel: EventGroupViewModel) {
            binding.viewmodel = viewmodel
            binding.root.setOnClickListener {
                val activity = it.context as MainActivity
                val data = EventGroupData(
                    viewmodel.name.value ?: "",
                    viewmodel.image.value
                )
                val bundle = bundleOf("eventGroupData" to data)
                activity.navigate(R.id.dayFragment, bundle)
            }
        }
    }
}
