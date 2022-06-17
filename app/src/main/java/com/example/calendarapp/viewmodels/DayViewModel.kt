package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DayViewModel : ViewModel() {
    var date = MutableLiveData<String>()

    fun changeDate(offset: Int) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        val cal = (calendar.clone() as Calendar).apply { add(Calendar.DATE, offset) }
        val curDate = sdf.format(cal.time)
        date.value = curDate
    }
}