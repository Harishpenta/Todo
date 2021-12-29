package com.curious.notes.utils

import java.util.*

object NotesUtility {
    fun convertMillisIntoDateString(millis: Long): String {
        val cl: Calendar = Calendar.getInstance()
        cl.timeInMillis = millis //here your time in miliseconds
        return "" + cl.get(Calendar.DAY_OF_MONTH).toString() +
                "-" + cl.get(Calendar.MONTH)
            .toString() + "-" + cl.get(Calendar.YEAR)
    }

    fun convertMillisIntoDateObject(millis: Long): Date {
        val cl: Calendar = Calendar.getInstance()
        cl.timeInMillis = millis //here your time in miliseconds
        return cl.time
    }

    fun convertMillisIntoTimeString(millis: Long): String {
        val cl: Calendar = Calendar.getInstance()
        cl.timeInMillis = millis //here your time in miliseconds
        return "" + cl.get(Calendar.HOUR_OF_DAY).toString() +
                "-" + cl.get(Calendar.MINUTE)
            .toString() + "-" + cl.get(Calendar.SECOND)
    }
}