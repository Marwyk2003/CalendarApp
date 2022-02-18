package com.example.calendarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var events: ArrayList<RvEvent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvEvents = findViewById<View>(R.id.rwEvents) as RecyclerView
        events = RvEvent.createEventsList(this)
        val adapter = RvAdapter(events)
        rvEvents.adapter = adapter
        rvEvents.layoutManager = LinearLayoutManager(this)
    }

    fun newEvent(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}
