package com.loitp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.base.BaseDao
import com.loitp.model.Task

@Dao
interface TaskDao : BaseDao<Task> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY id DESC")
    fun getListTaskAll(): List<Task>

    @Query("SELECT * FROM Task WHERE isComplete=1 ORDER BY id DESC")
    fun getListTaskComplete(): List<Task>

    @Query("SELECT * FROM Task WHERE isComplete=0 ORDER BY id DESC")
    fun getListTaskIncomplete(): List<Task>
}
