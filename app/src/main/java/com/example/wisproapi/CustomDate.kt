package com.example.wisproapi

import java.text.SimpleDateFormat
import java.util.*

object CustomDate {

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
    val currentDateandTime: String
        get() = sdf.format(Calendar.getInstance().time)

    fun getMonthBegginng():String {
        val text = StringBuffer(currentDateandTime)
        text.replace(11, 23, "00:00:00.000")
        text.replace(8,10,"01")

        return text.toString()
    }
}