package com.example.wisproapi

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

object CustomDate {

    val months: HashMap<String,String> = HashMap()

    init {
        months.put("01", "Enero")
        months.put("02", "Febrero")
        months.put("03", "Marzo")
        months.put("04", "Abril")
        months.put("05", "Mayo")
        months.put("06", "Junio")
        months.put("07", "Julio")
        months.put("08", "Agosto")
        months.put("09", "Septiembre")
        months.put("10", "Octubre")
        months.put("11", "Noviembre")
        months.put("12", "Diciembre")
    }
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
    val currentDateandTime: String
        get() = sdf.format(Calendar.getInstance().time)

    val currentMonth: String = currentDateandTime.substring(5,7)
    val currentDay: String = currentDateandTime.substring(8,10)


    fun getMonthBegginng():String {
        val text = StringBuffer(currentDateandTime)
        text.replace(11, 23, "00:00:00.000")
        text.replace(8,10,"01")

        return text.toString()
    }

    fun getMonth(): String {
        return months.get(currentMonth)!!
    }
}