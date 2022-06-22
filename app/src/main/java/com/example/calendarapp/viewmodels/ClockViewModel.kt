package com.example.calendarapp.viewmodels

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarapp.models.EventData
import java.util.*

class ClockViewModel : ViewModel() {
    val eventData = MutableLiveData(EventData())
    val currentTime = MutableLiveData("00:00:00")
    val isRunning = MutableLiveData(false)

    private val delay: Long = 1000 // 1s
    private lateinit var runnable: Runnable
    private val handler = Handler(Looper.getMainLooper())

    fun btnClicked() {
        val isR = isRunning.value ?: return
        if (isR) stopClock()
        else startClock()
        isRunning.value = !isR
    }

    private fun startClock() {
        runnable = Runnable {
            eventData.value?.endDate = Calendar.getInstance().time
            currentTime.value = timeDiff()
            handler.postDelayed(runnable, delay)
        }
        eventData.value?.endDate = Calendar.getInstance().time
        eventData.value?.startDate = Calendar.getInstance().time
        handler.postDelayed(runnable, delay)
    }

    private fun stopClock() {
        handler.removeCallbacks(runnable)
    }

    private fun timeDiff(): String {
        val start = eventData.value?.startDate
        val end = eventData.value?.endDate
        var diff: Long = 0
        if (start != null && end != null)
            diff = end.time - start.time
        val s = diff / 1000
        val m = s / 60
        val h = m / 60
        return "%02d:%02d:%02d".format(h, m, s)
    }
}