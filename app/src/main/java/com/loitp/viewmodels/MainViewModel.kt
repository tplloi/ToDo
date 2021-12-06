package com.loitp.viewmodels

import com.core.base.BaseViewModel
import com.loitp.db.TaskDatabase
import com.loitp.model.MessageEvent
import com.loitp.model.Task
import com.service.livedata.ActionData
import com.service.livedata.ActionLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val createTaskActionLiveData: ActionLiveData<ActionData<Task>> = ActionLiveData()
    val getListTaskAllActionLiveData: ActionLiveData<ActionData<List<Task>>> = ActionLiveData()

    fun createTask(task: Task) {
        createTaskActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            delay(500) // delay for demo purpose
            TaskDatabase.instance?.taskDao()?.insertTask(task = task)
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
            delay(500) // delay for demo purpose
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

    fun updateTask(task: Task) {
        ioScope.launch {
            delay(500) // delay for demo purpose
            TaskDatabase.instance?.taskDao()?.update(task)
            MessageEvent.postMsg(MessageEvent.UPDATE_TASK)
            createTaskActionLiveData.post(
                ActionData(
                    isDoing = false,
                    isSuccess = true,
                    data = task
                )
            )
        }
    }
}
