package com.loitp.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.annotation.LogTag
import com.core.base.BaseFragment
import com.loitp.R
import com.loitp.adapter.HeaderAdapter
import kotlinx.android.synthetic.main.frm_task_all.*

@LogTag("AllFragment")
class TaskAllFragment : BaseFragment() {
    private var concatAdapter = ConcatAdapter()
    private var headerAdapter: HeaderAdapter? = null

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
        headerAdapter = HeaderAdapter(getString(R.string.all))
        headerAdapter?.let { ha ->
            concatAdapter.addAdapter(ha)
        }
        recyclerView.adapter = concatAdapter
    }

    private fun setupViewModels() {
    }
}
