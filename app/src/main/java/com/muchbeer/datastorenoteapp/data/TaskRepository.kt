package com.muchbeer.datastorenoteapp.data

import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.*

object TaskRepository {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    // In a real app, this would be coming from a data source like a database
    val tasks = flowOf(
        listOf(
            Task(
                name = "Open codelab",
                deadline = simpleDateFormat.parse("2022-02-03")!!,
                priority = TaskPriority.LOW,
                completed = true
            ),
            Task(
                name = "Import project",
                deadline = simpleDateFormat.parse("2022-01-24")!!,
                priority = TaskPriority.MEDIUM,
                completed = true
            ),
            Task(
                name = "Check out the code",
                deadline = simpleDateFormat.parse("2022-01-26")!!,
                priority = TaskPriority.LOW
            ),
            Task(
                name = "Read about DataStore",
                deadline = simpleDateFormat.parse("2022-01-27")!!,
                priority = TaskPriority.HIGH
            ),
            Task(
                name = "Implement each step",
                deadline = Date(),
                priority = TaskPriority.MEDIUM
            ),
            Task(
                name = "Understand how to use DataStore",
                deadline = simpleDateFormat.parse("2022-01-03")!!,
                priority = TaskPriority.HIGH
            ),
            Task(
                name = "Understand how to migrate to DataStore",
                deadline = Date(),
                priority = TaskPriority.HIGH
            )
        )
    )
}