package com.roy93group.viewmodels

import com.loitp.core.base.BaseViewModel
import com.loitp.sv.liveData.ActionData
import com.loitp.sv.liveData.ActionLiveData
import com.roy93group.BuildConfig
import com.roy93group.db.TaskDatabase
import com.roy93group.model.MessageEvent
import com.roy93group.model.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
class MainViewModel : BaseViewModel() {

    val createTaskActionLiveData: ActionLiveData<ActionData<Task>> = ActionLiveData()
    val getListTaskAllActionLiveData: ActionLiveData<ActionData<List<Task>>> = ActionLiveData()
    val getListTaskCompleteActionLiveData: ActionLiveData<ActionData<List<Task>>> = ActionLiveData()
    val getListTaskIncompleteActionLiveData: ActionLiveData<ActionData<List<Task>>> =
        ActionLiveData()
    val updateTaskActionLiveData: ActionLiveData<ActionData<Task>> = ActionLiveData()
    val deleteTaskActionLiveData: ActionLiveData<ActionData<Task>> = ActionLiveData()

    fun createTask(task: Task) {
        createTaskActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            if (BuildConfig.DEBUG) {
                delay(500) // delay for demo purpose
            }
            TaskDatabase.instance?.taskDao()?.insert(task)
            MessageEvent.postMsg(MessageEvent.CREATE_TASK)
            createTaskActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = task
                )
            )
        }
    }

    fun getListTaskAll() {
        getListTaskAllActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            if (BuildConfig.DEBUG) {
                delay(500) // delay for demo purpose
            }
            val list = TaskDatabase.instance?.taskDao()?.getListTaskAll()
            getListTaskAllActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = list
                )
            )
        }
    }

    fun getListTaskComplete() {
        getListTaskCompleteActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            if (BuildConfig.DEBUG) {
                delay(500) // delay for demo purpose
            }
            val list = TaskDatabase.instance?.taskDao()?.getListTaskComplete()
            getListTaskCompleteActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = list
                )
            )
        }
    }

    fun getListTaskIncomplete() {
        getListTaskIncompleteActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            if (BuildConfig.DEBUG) {
                delay(500) // delay for demo purpose
            }
            val list = TaskDatabase.instance?.taskDao()?.getListTaskIncomplete()
            getListTaskIncompleteActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = list
                )
            )
        }
    }

    fun updateTask(task: Task) {
        updateTaskActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            if (BuildConfig.DEBUG) {
                delay(500) // delay for demo purpose
            }
            TaskDatabase.instance?.taskDao()?.update(task)
            MessageEvent.postMsg(MessageEvent.UPDATE_TASK)
            updateTaskActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = task
                )
            )
        }
    }

    fun deleteTask(task: Task) {
        deleteTaskActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            if (BuildConfig.DEBUG) {
                delay(500) // delay for demo purpose
            }
            TaskDatabase.instance?.taskDao()?.delete(task)
            MessageEvent.postMsg(MessageEvent.DELETE_TASK)
            deleteTaskActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = task
                )
            )
        }
    }
}
