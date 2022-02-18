package com.example.calendarapp

import android.content.Context

class RvEvent(val name: String, val timeStart: String, val timeEnd: String) {
    companion object {
        fun createEventsList(context: Context): ArrayList<RvEvent> {
            val events = ArrayList<RvEvent>()
            val dh = DataHandler()
            val data = dh.read(context)
            for (e in data) {
                events.add(RvEvent(e.name, e.timeStart, e.timeEnd))
            }
            return events
        }
    }
}
