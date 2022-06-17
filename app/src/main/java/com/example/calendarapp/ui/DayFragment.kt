package com.example.calendarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.calendarapp.R
import com.example.calendarapp.databinding.FragmentDayBinding
import com.example.calendarapp.models.RvEvent
import com.example.calendarapp.viewmodels.DayViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.*

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DayFragment : Fragment() {
    private lateinit var binding: FragmentDayBinding
    private val model: DayViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDayBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = model
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding.root
        val listFragment: ListFragment =
            childFragmentManager.findFragmentById(R.id.xd) as ListFragment
        val calendar = Calendar.getInstance()
        val dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7
        binding.dayTlWeek.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val dateDif = tab.position - dayOfWeek
                model.changeDate(dateDif)
                listFragment.initEventsList(model.date.value)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.dayTlWeek.getTabAt(dayOfWeek)?.select()
        return view
    }
}
