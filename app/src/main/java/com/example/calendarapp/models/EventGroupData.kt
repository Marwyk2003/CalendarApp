package com.example.calendarapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventGroupData(
    val name: String,
    val image: Int?
) : Parcelable
