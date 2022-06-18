package com.example.calendarapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventData(
    val id: Int?,
    val name: String,
    val desc: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val eventGroupName: String,
    val eventGroupImage: Int?
) : Parcelable
