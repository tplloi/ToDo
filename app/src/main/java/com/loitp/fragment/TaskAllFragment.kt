package com.loitp.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.annotation.LogTag
import com.core.base.BaseFragment
import com.loitp.R
import com.loitp.adapter.HeaderAdapter
import com.loitp.adapter.TaskAdapter
import kotlinx.android.synthetic.main.frm_task_all.*

@LogTag("AllFragment")
class TaskAllFragment : BaseFragment() {
    private var concatAdapter = ConcatAdapter()
    private var headerAdapter = HeaderAdapter()
    private var taskAdapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_task_all
    }

    private fun setupViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        concatAdapter.addAdapter(headerAdapter)
        concatAdapter.addAdapter(taskAdapter)
        recyclerView.adapter = concatAdapter
        headerAdapter.setData(getString(R.string.all))
    }

    private fun setupViewModels() {
    }
}
