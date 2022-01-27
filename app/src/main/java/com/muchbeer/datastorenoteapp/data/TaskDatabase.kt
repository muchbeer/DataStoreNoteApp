package com.muchbeer.datastorenoteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities =[Task::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object {

        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context: Context) : TaskDatabase {

            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "task.db")
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}