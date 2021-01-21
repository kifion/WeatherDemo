package com.example.weatherapp.domain.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DatetimeUtils {
    companion object {
        fun getTime(timezone: String): String {
            val dateFormat: DateFormat = SimpleDateFormat("hh.mm aa")
            dateFormat.timeZone = TimeZone.getTimeZone(timezone)
            return dateFormat.format(Date()).toString()
        }

        fun getDate(timezone: String): String {
            val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy")
            dateFormat.timeZone = TimeZone.getTimeZone(timezone)
            return dateFormat.format(Date()).toString()
        }
    }
}