package com.roy93group.test

import com.roy93group.db.TaskDatabase
import com.roy93group.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
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

    fun getListTaskComplete(result: ((Boolean) -> Unit)) {
//        result.invoke(true)

        ioScope.launch {
            val list = TaskDatabase.instance?.taskDao()?.getListTaskComplete()
            if (list.isNullOrEmpty()) {
                result.invoke(false)
            } else {
                result.invoke(true)
            }
        }
    }

    fun getListTaskIncomplete(result: ((Boolean) -> Unit)) {
//        result.invoke(true)

        ioScope.launch {
            val list = TaskDatabase.instance?.taskDao()?.getListTaskIncomplete()
            if (list.isNullOrEmpty()) {
                result.invoke(false)
            } else {
                result.invoke(true)
            }
        }
    }

    fun updateTask(task: Task?, result: ((Boolean) -> Unit)) {
//        result.invoke(true)

        val instance = TaskDatabase.instance
        if (task == null || task.title.isEmpty() || task.id.isEmpty() || instance == null) {
            result.invoke(false)
        } else {
            ioScope.launch {
                instance.taskDao().update(task)
                result.invoke(true)
            }
        }
    }

    fun deleteTask(task: Task?, result: ((Boolean) -> Unit)) {
//        result.invoke(true)

        val instance = TaskDatabase.instance
        if (task == null || task.id.isEmpty() || instance == null) {
            result.invoke(false)
        } else {
            ioScope.launch {
                instance.taskDao().delete(task)
                result.invoke(true)
            }
        }
    }
}
