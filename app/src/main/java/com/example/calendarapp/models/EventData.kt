package com.example.calendarapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class EventData(
    var id: Int? = -1, // TODO
    var name: String = "",
    var desc: String = "",
    var startDate: Date? = null,
    var endDate: Date? = null,
    var eventGroupName: String = "",
    var eventGroupImage: Int? = null
) : Parcelable
