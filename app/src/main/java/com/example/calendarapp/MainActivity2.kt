package com.example.calendarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun save(view: View) {
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val info = findViewById<EditText>(R.id.editTextInfo).text.toString()
        val timeStart = findViewById<EditText>(R.id.editTextStartTime).text.toString()
        val timeEnd = findViewById<EditText>(R.id.editTextEndTime).text.toString()

        val ed = EventData(name,info,timeStart,timeEnd)
        val dh = DataHandler()
        dh.write(ed, this)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
