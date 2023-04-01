package com.roy93group.fragment

import android.os.Bundle
import android.view.View
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseFragment
import com.loitp.core.ext.replaceFragment
import com.loitp.core.ext.setSafeOnClickListener
import com.roy93group.R
import kotlinx.android.synthetic.main.frm_home.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("HomeFragment")
class HomeFragment : BaseFragment() {

//    private var mainViewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModels()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_home
    }

    private fun setupViews() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navAll -> {
                    goToNavAll()
                }
                R.id.navComplete -> {
                    goToNavComplete()
                }
                R.id.navIncomplete -> {
                    goToNavIncomplete()
                }
                else -> {
                    goToNavAll()
                }
            }
            return@setOnItemSelectedListener true
        }
        btCreate.setSafeOnClickListener {
            createTask()
        }
        goToNavAll()
    }

    private fun setupViewModels() {
//        mainViewModel = getViewModel(MainViewModel::class.java)
//        mainViewModel?.let { mvm ->
//        }
    }

    private fun goToNavAll() {
        activity?.replaceFragment(
            containerFrameLayoutIdRes = R.id.fcv,
            fragment = TaskAllFragment(),
            isAddToBackStack = false
        )
    }

    private fun goToNavComplete() {
        activity?.replaceFragment(
            containerFrameLayoutIdRes = R.id.fcv,
            fragment = TaskCompleteFragment(),
            isAddToBackStack = false
        )
    }

    private fun goToNavIncomplete() {
        activity?.replaceFragment(
            containerFrameLayoutIdRes = R.id.fcv,
            fragment = TaskIncompleteFragment(),
            isAddToBackStack = false
        )
    }

    private fun createTask() {
        val bottomSheetFragment = TaskCreateFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }
}
