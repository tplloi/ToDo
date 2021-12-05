package com.loitp.fragment

import android.os.Bundle
import android.view.View
import com.annotation.LogTag
import com.core.base.BaseFragment
import com.core.utilities.LScreenUtil
import com.loitp.R
import com.loitp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.frm_home.*

@LogTag("HomeFragment")
class HomeFragment : BaseFragment() {

    private var mainViewModel: MainViewModel? = null

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
        goToNavAll()
    }

    private fun setupViewModels() {
        mainViewModel = getViewModel(MainViewModel::class.java)
        mainViewModel?.let { mvm ->
        }
    }

    private fun goToNavAll() {
        LScreenUtil.replaceFragment(
            activity = requireActivity(),
            containerFrameLayoutIdRes = R.id.fragmentContainerView,
            fragment = AllFragment(),
            isAddToBackStack = false
        )
    }

    private fun goToNavComplete() {
        LScreenUtil.replaceFragment(
            activity = requireActivity(),
            containerFrameLayoutIdRes = R.id.fragmentContainerView,
            fragment = CompleteFragment(),
            isAddToBackStack = false
        )
    }

    private fun goToNavIncomplete() {
        LScreenUtil.replaceFragment(
            activity = requireActivity(),
            containerFrameLayoutIdRes = R.id.fragmentContainerView,
            fragment = IncompleteFragment(),
            isAddToBackStack = false
        )
    }
}
