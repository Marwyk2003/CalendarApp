package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarapp.models.EventData

class EventViewModel : ViewModel() {
    val eventData = MutableLiveData<EventData>();
}
