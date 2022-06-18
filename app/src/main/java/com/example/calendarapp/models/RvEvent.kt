package com.example.calendarapp.models

class RvEvent(val eData: EventData) {
    companion object {
        fun createEventsList(data: List<EventData>): ArrayList<RvEvent> {
            val events = ArrayList<RvEvent>()
            for (e in data) {
                events.add(RvEvent(e))
            }
            return events
        }
    }
}
