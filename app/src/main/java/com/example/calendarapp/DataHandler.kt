package com.example.calendarapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import kotlin.math.max

class DataHandler {

    fun lastId(context: Context?): Int {
        val fullData = read(context)
        var maxId = -1
        fullData.forEach { x ->
            maxId = max(maxId, x.id)
        }
        return maxId
    }

    fun write(data: List<EventData>, context: Context?) {
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

    fun append(event: EventData, context: Context?) {
        val savedData = this.read(context)
        val newData = savedData + event
        write(newData, context)
    }

    fun read(context: Context?): List<EventData> {
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
        return listOf<EventData>()
    }

    fun removeEvent(context: Context?, eventId: Int) {
        var eventData = read(context)
        if (eventData.isEmpty()) {
            return
        }
        val newEventData = eventData.filter { x -> x.id != eventId }
        write(newEventData, context)
    }

    fun readDate(context: Context?, date: String): List<EventData> {
        val fullData = this.read(context)
        return fullData.filter { x -> x.date == date }
    }
}

data class EventData(
    val id: Int,
    val name: String,
    val info: String,
    val timeStart: String,
    val timeEnd: String,
    val date: String,
)
