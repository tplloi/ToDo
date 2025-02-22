package com.roy93group.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseFragment
import com.roy93group.R
import com.roy93group.adapter.HeaderAdapter
import com.roy93group.adapter.TaskAdapter
import com.roy93group.model.MessageEvent
import com.roy93group.model.Task
import com.roy93group.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.frm_task_all.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("TaskAllFragment")
class TaskAllFragment : BaseFragment() {
    private var mainViewModel: MainViewModel? = null
    private var concatAdapter = ConcatAdapter()
    private var headerAdapter = HeaderAdapter()
    private var taskAdapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
        getListTaskAll()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_task_all
    }

    private fun getListTaskAll() {
        mainViewModel?.getListTaskAll()
    }

    private fun setupViews() {
        rv.layoutManager = LinearLayoutManager(context)
        headerAdapter.setData(getString(R.string.all))
        concatAdapter.addAdapter(headerAdapter)
        taskAdapter.onClickCbCompleteListener = { task ->
            handleCheckboxComplete(task)
        }
        taskAdapter.onClickDeleteListener = { task ->
            handleDeleteTask(task)
        }
        concatAdapter.addAdapter(taskAdapter)
        rv.adapter = concatAdapter
    }

    private fun setupViewModels() {
        mainViewModel = getViewModel(MainViewModel::class.java)
        mainViewModel?.let { vm ->
            vm.getListTaskAllActionLiveData.observe(viewLifecycleOwner) { actionData ->
                val isDoing = actionData.isDoing
                val isSuccess = actionData.isSuccess

                if (isDoing == true) {
                    pbwp10.showProgressBar()
                } else {
                    pbwp10.hideProgressBar()
                    if (isSuccess == true) {
                        actionData.data?.let { list ->
                            taskAdapter.setData(list)
                            tvNoData.isVisible = list.isEmpty()
                        }
                    }
                }
            }
            vm.updateTaskActionLiveData.observe(viewLifecycleOwner) { actionData ->
                val isDoing = actionData.isDoing
                if (isDoing == true) {
                    pbwp10.showProgressBar()
                } else {
                    pbwp10.hideProgressBar()
                }
            }
            vm.deleteTaskActionLiveData.observe(viewLifecycleOwner) { actionData ->
                val isDoing = actionData.isDoing
                if (isDoing == true) {
                    pbwp10.showProgressBar()
                } else {
                    pbwp10.hideProgressBar()
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent?) {
        event?.msg?.let {
//            logD(">>> onMessageEvent $it")
            if (it == MessageEvent.CREATE_TASK || it == MessageEvent.DELETE_TASK) {
                getListTaskAll()
            }
        }
    }

    private fun handleCheckboxComplete(task: Task) {
        task.isComplete = !task.isComplete
        mainViewModel?.updateTask(task)
    }

    private fun handleDeleteTask(task: Task) {
        showBottomSheetOptionFragment(
            title = getString(R.string.warning),
            message = getString(R.string.delete_msg),
            textButton1 = getString(R.string.cancel),
            textButton2 = getString(R.string.confirm),
            onClickButton2 = {
                mainViewModel?.deleteTask(task)
            }
        )
    }
}
