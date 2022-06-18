package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarapp.models.EventData

class EventViewModel : ViewModel() {
    val id = MutableLiveData<Int>()
    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
    val startTime = MutableLiveData<String>()
    val endTime = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val eventGroupName = MutableLiveData<String>()
    val eventGroupImage = MutableLiveData<Int?>()

    fun init(data: EventData) {
        id.value = data.id
        name.value = data.name
        desc.value = data.desc
        startTime.value = data.startTime
        endTime.value =data.endTime
        date.value = data.date
        eventGroupName.value = data.eventGroupName
        eventGroupImage.value = data.eventGroupImage
    }
}
