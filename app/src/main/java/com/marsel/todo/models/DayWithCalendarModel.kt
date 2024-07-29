package com.marsel.todo.models

data class DayWithCalendarModel(
    val dayOfWeek: String,
    val dayOfMonth: Int,
    val month: String,
    val year: String,
)