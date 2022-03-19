package com.example.calendarapp

class RvEvent(val id: Int, val name: String, val startTime: String, val endTime: String) {
    companion object {
        fun createEventsList(data: List<EventData>): ArrayList<RvEvent> {
            val events = ArrayList<RvEvent>()
            for (e in data) {
                events.add(RvEvent(e.id, e.name, e.startTime, e.endTime))
            }
            return events
        }
    }
}
