package com.example.calendarapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapp.R
import com.example.calendarapp.databinding.FragmentListBinding
import com.example.calendarapp.models.EventData
import com.example.calendarapp.models.RvEvent
import com.example.calendarapp.services.DataHandler
import com.example.calendarapp.services.EventRvAdapter
import com.example.calendarapp.viewmodels.EventViewModel

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var events: ArrayList<EventViewModel>

    private var eventGroupName: String = "";
    private var eventGroupImage: Int? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding.root
        initEventsList()
        binding.listRvEvents.layoutManager = LinearLayoutManager(container?.context)
        binding.listBtnNewEvent.setOnClickListener {
            val activity = it.context as MainActivity
            val data = EventData(null, "", "", "", "", "", eventGroupName, eventGroupImage)
            val bundle = bundleOf("eventData" to data)
            activity.navigate(R.id.formFragment, bundle)
        }
        return view
    }

    fun initEventsList(date: String? = null, egName: String? = null) {
        val data = DataHandler().readFilter(context, date)
        eventGroupName = egName ?: ""
        eventGroupImage = this.resources.getIdentifier(
            eventGroupName,
            "drawable",
            binding.root.context.packageName
        )
        val eData = RvEvent.createEventsList(data).map { it -> it.eData }
        events =
            eData.map { it -> EventViewModel().apply { init(it) } } as ArrayList<EventViewModel>
        binding.listRvEvents.adapter = EventRvAdapter(events)
    }
}
