package com.example.calendarapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventGroupViewModel : ViewModel() {
    var name = MutableLiveData<String>()
    var image = MutableLiveData<Int>()

    fun init(_name: String, _imagePath: Int) {
        name.value = _name
        image.value = _imagePath
    }
}
