package com.example.calendarapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapp.databinding.FragmentEventGroupBinding
import com.example.calendarapp.models.EventGroupData
import com.example.calendarapp.services.EventGroupRvAdapter
import com.example.calendarapp.viewmodels.EventGroupViewModel

class EventGroupFragment : Fragment() {
    private lateinit var binding: FragmentEventGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentEventGroupBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding.root
        initEventsList()
        binding.rvEventGroup.layoutManager = LinearLayoutManager(container?.context)
        return view
    }

    private fun initEventsList() {
        val events = ArrayList<EventGroupViewModel>().apply {
            add(EventGroupViewModel().apply {
                eventGroupData.value = EventGroupData("languages", getResId("languages"))
            })
            add(EventGroupViewModel().apply {
                eventGroupData.value = EventGroupData("programming", getResId("programming"))
            })
        }
        binding.rvEventGroup.adapter = EventGroupRvAdapter(events)
    }

    private fun getResId(name: String) =
        this.resources.getIdentifier(name, "drawable", binding.root.context.packageName)
}
