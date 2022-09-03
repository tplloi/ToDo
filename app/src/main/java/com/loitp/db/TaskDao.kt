package com.loitp.db

import androidx.room.Dao
import androidx.room.Query
import com.loitp.model.Task
import com.loitpcore.core.base.BaseDao

@Dao
interface TaskDao : BaseDao<Task> {
    @Query("SELECT * FROM Task ORDER BY id DESC")
    fun getListTaskAll(): List<Task>

    @Query("SELECT * FROM Task WHERE isComplete=1 ORDER BY id DESC")
    fun getListTaskComplete(): List<Task>

    @Query("SELECT * FROM Task WHERE isComplete=0 ORDER BY id DESC")
    fun getListTaskIncomplete(): List<Task>
}
