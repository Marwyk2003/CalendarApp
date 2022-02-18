package com.example.calendarapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import java.lang.Exception

class DataHandler {
    fun write(event: EventData, context: Context) {
        val savedData = this.read(context)
        val newData = savedData + event
        val gson = Gson()
        val jsonString = gson.toJson(newData)

        val dir = File(context.filesDir, "calendarApp")
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

    fun read(context: Context): List<EventData> {
        try {
            val dir = File(context.filesDir, "calendarApp")
            val file = File(dir, "EventData.txt")
            val reader = file.bufferedReader()
            val json = reader.use { it.readText() }
            val gson = Gson()
            val typeToken = object : TypeToken<List<EventData>>() {}.type
            return gson.fromJson(json, typeToken)
        } catch (e: Exception) {
            // TODO
            return emptyList<EventData>()
        }
    }
}

data class EventData(
    val name: String,
    val info: String,
    val timeStart: String,
    val timeEnd: String,
)
