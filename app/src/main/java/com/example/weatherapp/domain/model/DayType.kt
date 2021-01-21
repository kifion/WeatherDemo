package com.example.weatherapp.domain.model

enum class DayType(val code: Int, val type: String) {
    MONDAY(0, "Mon"),
    TUESDAY(1, "Tue"),
    WEDNESDAY(2, "Wen"),
    THURSDAY(3, "Thu"),
    FRIDAY(4, "Fri"),
    SATURDAY(5, "Sat"),
    SUNDAY(6, "Sun");

    companion object {
        fun getTypeByCode(code: Int): String {
            return when (code) {
                MONDAY.code -> MONDAY.type
                TUESDAY.code -> TUESDAY.type
                WEDNESDAY.code -> WEDNESDAY.type
                THURSDAY.code -> THURSDAY.type
                FRIDAY.code -> FRIDAY.type
                SATURDAY.code -> SATURDAY.type
                SUNDAY.code -> SUNDAY.type
                else -> {
                    throw Exception("Unknown day of week")
                }
            }
        }
    }
}