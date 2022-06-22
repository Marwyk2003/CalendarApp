package com.example.calendarapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.calendarapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {
    @BindingAdapter("app:runningIcon")
    @JvmStatic
    fun runningIcon(view: FloatingActionButton, isRunning: Boolean) {
        if (isRunning) view.setImageResource(R.drawable.ic_stop)
        else view.setImageResource(R.drawable.ic_play)
    }

    @BindingAdapter("app:dateString")
    @JvmStatic
    fun dateString(view: TextView, date: Date) {
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        view.text = sdf.format(date)
    }

    @BindingAdapter("app:timeString")
    @JvmStatic
    fun timeString(view: TextView, date: Date): String {
        val sdf = SimpleDateFormat("hh:mm:ss")
        view.text = sdf.format(date)
        return sdf.format(date)
    }
}