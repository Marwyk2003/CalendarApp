package com.example.calendarapp

class RvEvent(val id: Int, val name: String, val timeStart: String, val timeEnd: String) {
    companion object {
        fun createEventsList(data: List<EventData>): ArrayList<RvEvent> {
            val events = ArrayList<RvEvent>()
            for (e in data) {
                events.add(RvEvent(e.id, e.name, e.timeStart, e.timeEnd))
            }
            return events
        }
    }
}
