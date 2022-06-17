package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class FormViewModel: ViewModel() {
    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
    val timeStart = MutableLiveData<String>()
    val timeEnd = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val dateHint = MutableLiveData<String>()

    fun currentDate(): String {
        val today = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        return sdf.format(today)
    }


}