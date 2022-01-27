package com.muchbeer.datastorenoteapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@TypeConverters
enum class TaskPriority {
    HIGH, MEDIUM, LOW
}

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val deadline: Date,
    val priority: TaskPriority,
    val completed: Boolean = false
)