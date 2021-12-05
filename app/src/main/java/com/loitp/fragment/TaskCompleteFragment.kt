package com.loitp.fragment

import android.os.Bundle
import android.view.View
import com.annotation.LogTag
import com.core.base.BaseFragment
import com.loitp.R

@LogTag("CompleteFragment")
class TaskCompleteFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_task_complete
    }

    private fun setupViews() {
    }

    private fun setupViewModels() {
    }
}
