package com.example.calendarapp.models

class RvEventGroup(val egData: EventGroupData) {
    companion object {
        fun createEventsList(data: List<EventGroupData>): ArrayList<RvEventGroup> {
            val activities = ArrayList<RvEventGroup>()
            for (a in data) {
                activities.add(RvEventGroup(a))
            }
            return activities
        }
    }
}