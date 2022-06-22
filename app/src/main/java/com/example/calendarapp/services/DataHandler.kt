package com.example.calendarapp.services

import android.content.Context
import com.example.calendarapp.models.EventData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max

class DataHandler {
    fun lastId(context: Context?): Int {
        val fullData = read(context)
        var maxId = -1
        fullData.forEach { x ->
            maxId = max(maxId, x.id ?: -1)
        }
        return maxId
    }

    private fun write(data: List<EventData>, context: Context?) {
        val gson = Gson()
        val jsonString = gson.toJson(data)
        val dir = File(context?.filesDir, "calendarApp")
        if (!dir.exists()) {
            dir.mkdir()
        }
        try {
            val file = File(dir, "EventData.txt")
            val writer = FileWriter(file)
            writer.append(jsonString)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            // TODO
        }
    }

    fun append(context: Context?, event: EventData) {
        val savedData = this.read(context)
        val newData = savedData + event
        write(newData, context)
    }

    private fun read(context: Context?): List<EventData> {
        try {
            val dir = File(context?.filesDir, "calendarApp")
            val file = File(dir, "EventData.txt")
            val reader = file.bufferedReader()
            val json = reader.use { it.readText() }
            val gson = Gson()
            val typeToken = object : TypeToken<List<EventData>>() {}.type
            return gson.fromJson(json, typeToken)
        } catch (e: Exception) {
            // TODO
        }
        return listOf()
    }

    fun removeEvent(context: Context?, eventId: Int) {
        val eventData = read(context)
        if (eventData.isEmpty()) {
            return
        }
        val newEventData = eventData.filter { x -> x.id != eventId }
        write(newEventData, context)
    }

    fun readFilter(
        context: Context?,
        date: String? = null,
        eventGroup: String? = null
    ): List<EventData> {
        val fullData = this.read(context)

        return fullData.filter { // TODO between start end end dates
            (date == null || date2string(it.startDate) == date) && (eventGroup == null || it.eventGroupName == eventGroup)
        }
    }

    private fun date2string(date: Date?): String { // TODO
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        if (date == null) return ""
        return sdf.format(date)
    }

    fun editEvent(context: Context?, event: EventData) {
        val fullData = read(context) as MutableList
        if (fullData.isEmpty()) {
            return
        }
        val newEventData = fullData.map { x -> if (x.id == event.id) event else x }
        write(newEventData, context)
    }
}
