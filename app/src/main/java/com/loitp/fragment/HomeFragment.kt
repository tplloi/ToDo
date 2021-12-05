package com.loitp.fragment

import android.os.Bundle
import android.view.View
import com.annotation.LogTag
import com.core.base.BaseFragment
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
                }
                R.id.navComplete -> {
                }
                R.id.navIncomplete -> {
                }
                else -> {
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setupViewModels() {
        mainViewModel = getViewModel(MainViewModel::class.java)
        mainViewModel?.let { mvm ->
        }
    }
}
