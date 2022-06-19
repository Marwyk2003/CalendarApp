package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarapp.models.EventGroupData

class EventGroupViewModel : ViewModel() {
    var eventGroupData = MutableLiveData<EventGroupData>()
}
