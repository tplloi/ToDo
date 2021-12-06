package com.loitp.viewmodels

import com.core.base.BaseViewModel
import com.loitp.db.TaskDatabase
import com.loitp.model.Task
import com.service.livedata.ActionData
import com.service.livedata.ActionLiveData
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val createTaskActionLiveData: ActionLiveData<ActionData<Task>> = ActionLiveData()

    fun createTask(task: Task) {
        createTaskActionLiveData.set(
            ActionData(isDoing = true)
        )
        ioScope.launch {
            TaskDatabase.instance?.taskDao()?.insertTask(task = task)
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
