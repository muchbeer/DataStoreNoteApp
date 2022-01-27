package com.muchbeer.datastorenoteapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task : List<Task>)

    @Query("SELECT * FROM task")
    fun getAllTask() : LiveData<List<Task>>

    @Query("SELECT * FROM task")
    fun getAllTaskFlow() : Flow<List<Task>>
}