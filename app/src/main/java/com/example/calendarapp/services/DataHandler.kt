package com.example.calendarapp

import android.content.Context
import android.os.Parcelable
import com.example.calendarapp.models.EventData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import kotlin.math.max
import java.io.Serializable

class DataHandler {

    fun lastId(context: Context?): Int {
        val fullData = read(context)
        var maxId = -1
        fullData.forEach { x ->
            maxId = max(maxId, x.id)
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
        return listOf()
    }

    fun readEvent(context: Context?, eventId: Int): EventData {
        val fullData = this.read(context)
        return fullData.first { x -> x.id == eventId }
    }

    fun removeEvent(context: Context?, eventId: Int) {
        val eventData = read(context)
        if (eventData.isEmpty()) {
            return
        }
        val newEventData = eventData.filter { x -> x.id != eventId }
        write(newEventData, context)
    }

    fun readDate(context: Context?, date: String?): List<EventData> {
        val fullData = this.read(context)
        return if (date == null) fullData else fullData.filter { x -> x.date == date }
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
