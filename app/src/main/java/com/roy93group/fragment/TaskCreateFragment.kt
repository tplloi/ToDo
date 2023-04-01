package com.roy93group.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.loitp.core.base.BaseBottomSheetFragment
import com.loitp.core.ext.setSafeOnClickListener
import com.roy93group.R
import com.roy93group.activity.MainActivity
import com.roy93group.model.Task
import com.roy93group.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.frm_task_create.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
class TaskCreateFragment : BaseBottomSheetFragment(
    layoutId = R.layout.frm_task_create,
    height = WindowManager.LayoutParams.WRAP_CONTENT,
    isDraggable = true,
    firstBehaviourState = BottomSheetBehavior.STATE_COLLAPSED
) {
    private var mainViewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViews() {
        etDescription.setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                view.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener false
        }
        btCreate.setSafeOnClickListener {
            handleCreateTask()
        }
    }

    private fun setupViewModels() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel?.let { vm ->
            vm.createTaskActionLiveData.observe(this) { actionData ->
                val isDoing = actionData.isDoing
                val isSuccess = actionData.isSuccess

                if (isDoing == true) {
                    showDialogProgress()
                } else {
                    hideDialogProgress()
                    if (isSuccess == true) {
                        val task = actionData.data
//                        logD("<<< task " + BaseApplication.gson.toJson(task))
                        task?.let {
                            showToast(getString(R.string.create_successfully))
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        if (activity is MainActivity) {
            (activity as MainActivity).showShortInformation(msg)
        }
    }

    private fun handleCreateTask() {
        val title = etTitle.text.toString().trim()
        if (title.isEmpty()) {
            showToast(getString(R.string.title_cannot_be_null_or_empty))
            return
        }
        val description = etDescription.text.toString().trim()
        val task = Task().apply {
            this.title = title
            this.id = System.currentTimeMillis().toString()
            this.isComplete = cbComplete.isChecked
            this.description = description
        }
        mainViewModel?.createTask(task)
    }
}
