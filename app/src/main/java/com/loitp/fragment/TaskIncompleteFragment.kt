package com.loitp.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.annotation.LogTag
import com.core.base.BaseFragment
import com.loitp.R
import com.loitp.adapter.HeaderAdapter
import com.loitp.adapter.TaskAdapter
import com.loitp.model.MessageEvent
import com.loitp.model.Task
import com.loitp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.frm_task_all.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@LogTag("IncompleteFragment")
class TaskIncompleteFragment : BaseFragment() {
    private var mainViewModel: MainViewModel? = null
    private var concatAdapter = ConcatAdapter()
    private var headerAdapter = HeaderAdapter()
    private var taskAdapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
        getListTaskIncomplete()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_task_incomplete
    }

    private fun getListTaskIncomplete() {
        mainViewModel?.getListTaskIncomplete()
    }

    private fun setupViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        headerAdapter.setData(getString(R.string.incomplete))
        concatAdapter.addAdapter(headerAdapter)
        taskAdapter.onClickCbCompleteListener = { task ->
            handleCheckboxComplete(task)
        }
        taskAdapter.onClickDeleteListener = { task ->
            handleDeleteTask(task)
        }
        concatAdapter.addAdapter(taskAdapter)
        recyclerView.adapter = concatAdapter
    }

    private fun setupViewModels() {
        mainViewModel = getViewModel(MainViewModel::class.java)
        mainViewModel?.let { vm ->
            vm.getListTaskIncompleteActionLiveData.observe(this, { actionData ->
                val isDoing = actionData.isDoing
                val isSuccess = actionData.isSuccess

                if (isDoing == true) {
                    progressBar.showProgressBar()
                } else {
                    progressBar.hideProgressBar()
                    if (isSuccess == true) {
                        actionData.data?.let { list ->
                            taskAdapter.setData(list)
                            tvNoData.isVisible = list.isNullOrEmpty()
                        }
                    }
                }
            })
            vm.updateTaskActionLiveData.observe(this, { actionData ->
                val isDoing = actionData.isDoing
                if (isDoing == true) {
                    progressBar.showProgressBar()
                } else {
                    progressBar.hideProgressBar()
                }
            })
            vm.deleteTaskActionLiveData.observe(this, { actionData ->
                val isDoing = actionData.isDoing
                if (isDoing == true) {
                    progressBar.showProgressBar()
                } else {
                    progressBar.hideProgressBar()
                }
            })
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent?) {
        event?.msg?.let {
            logD(">>> onMessageEvent $it")
            getListTaskIncomplete()
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
