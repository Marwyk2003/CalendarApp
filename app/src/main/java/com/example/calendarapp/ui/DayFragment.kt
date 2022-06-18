package com.example.calendarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.calendarapp.R
import com.example.calendarapp.databinding.FragmentDayBinding
import com.example.calendarapp.viewmodels.DayViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.*

class DayFragment : Fragment() {
    private lateinit var binding: FragmentDayBinding
    private val model: DayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDayBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = model
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding.root
        val bundle = arguments
        if (bundle != null) {
            val args = DayFragmentArgs.fromBundle(bundle)
            model.eventGroupName.value = args.eventGroupData?.name
        }
        val listFragment: ListFragment =
            childFragmentManager.findFragmentById(R.id.day_list) as ListFragment // TODO
        val calendar = Calendar.getInstance()
        val dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7
        binding.dayTlWeek.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val dateDif = tab.position - dayOfWeek
                model.changeDate(dateDif)
                listFragment.initEventsList(model.date.value, model.eventGroupName.value)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.dayTlWeek.getTabAt(dayOfWeek)?.select()
        return view
    }
}
