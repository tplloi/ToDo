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
    fun insertTask(list: Task)

    @Query("SELECT * FROM Task WHERE isComplete=1")
    fun getListTaskIncomplete(): List<Task>
}
