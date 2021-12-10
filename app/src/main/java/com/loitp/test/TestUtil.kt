package com.loitp.test

import com.loitp.db.TaskDatabase
import com.loitp.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object TestUtil {
    private val viewModelJob = Job()
    private val ioContext = viewModelJob + Dispatchers.IO // background context
    private val ioScope = CoroutineScope(ioContext)

    fun createTask(task: Task?, result: ((Boolean) -> Unit)) {
//        result.invoke(true)

        val instance = TaskDatabase.instance
        if (task == null || task.title.isEmpty() || instance == null) {
            result.invoke(false)
        } else {
            ioScope.launch {
                instance.taskDao().insert(task)
                result.invoke(true)
            }
        }
    }

    fun getListTaskAll(result: ((Boolean) -> Unit)) {
//        result.invoke(true)

        ioScope.launch {
            val list = TaskDatabase.instance?.taskDao()?.getListTaskAll()
            if (list.isNullOrEmpty()) {
                result.invoke(false)
            } else {
                result.invoke(true)
            }
        }
    }
}
