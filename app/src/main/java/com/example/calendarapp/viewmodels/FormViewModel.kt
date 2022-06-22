package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarapp.models.EventData
import java.text.SimpleDateFormat
import java.util.*

class FormViewModel : ViewModel() {
    val eventData = MutableLiveData<EventData>()
    val dateHint = MutableLiveData<String>()

    val startDate = MutableLiveData<String>()
    val startTime = MutableLiveData<String>()
    val endDate = MutableLiveData<String>()
    val endTime = MutableLiveData<String>()

    fun currentDate(): String {
        val today = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        return sdf.format(today)
    }

    fun setDate(): Boolean {
        eventData.value?.startDate = Date()
        val sdf = SimpleDateFormat("yyyy.MM.dd_hh:mm:ss")
        val startDateString = "${startDate.value}_${startTime.value}"
        val endDateString = "${startDate.value}_${startTime.value}"
        try {
            eventData.value?.startDate = sdf.parse(startDateString)
            eventData.value?.endDate = sdf.parse(endDateString)
        } catch (e: Error) {
            return false
        }
        return true
    }

    fun init(ed: EventData?) {
        eventData.value = ed ?: return
        val sdfDate = SimpleDateFormat("yyyy.MM.dd")
        val sdfTime = SimpleDateFormat("hh:mm:ss")
        startDate.value = sdfDate.format(ed.startDate)
        startTime.value = sdfTime.format(ed.startDate)
        endDate.value = sdfDate.format(ed.endDate)
        endTime.value = sdfTime.format(ed.endDate)
    }
}
