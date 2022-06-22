package com.example.calendarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.calendarapp.databinding.FragmentClockBinding
import com.example.calendarapp.viewmodels.ClockViewModel

class ClockFragment : Fragment() {
    private lateinit var binding: FragmentClockBinding
    private val model: ClockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentClockBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = model
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}
