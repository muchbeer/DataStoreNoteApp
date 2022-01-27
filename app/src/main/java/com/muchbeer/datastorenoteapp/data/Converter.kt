package com.muchbeer.datastorenoteapp.data

import androidx.room.TypeConverter
import java.util.*

class Converter {
    @TypeConverter
    fun fromStringToTask(taskValues: String): TaskPriority {
       return enumValueOf<TaskPriority>(taskValues)
    }

    @TypeConverter
    fun fromTaskToString(taskPriority: TaskPriority): String {
        return taskPriority.name
    }

    @TypeConverter
    fun toDate(value:Long): Date {
        //If your result always returns 1970, then comment this line
        //val date =  Date(value)

        //If your result always returns 1970, then uncomment this line
        val date = Date(value*1000L)

        return Date(value*1000L)
    }

    @TypeConverter
        fun fromDate(date:Date):Long{
        return date.time
    }
}