package com.loitp.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.annotation.LogTag
import com.core.base.BaseApplication
import com.core.base.BaseFragment
import com.core.utilities.LSocialUtil
import com.loitp.R
import com.loitp.viewmodels.MainViewModel
import com.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.frm_home.*

@LogTag("HomeFragment")
class HomeFragment : BaseFragment() {

    private var mainViewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModels()
        context?.let {
            mainViewModel?.loadListChap()
        }
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_home
    }

    private fun setupViews() {
        btGithub.setSafeOnClickListener {
            LSocialUtil.openUrlInBrowser(
                context = activity,
                url = "https://github.com/tplloi/basemaster.demo"
            )
        }
    }

    private fun setupViewModels() {
        mainViewModel = getViewModel(MainViewModel::class.java)
        mainViewModel?.let { mvm ->
            mvm.eventLoading.observe(
                viewLifecycleOwner,
                { isLoading ->
                    indicatorView.isVisible = isLoading
                }
            )

            mvm.listChapLiveData.observe(
                viewLifecycleOwner,
                { listChap ->
                    logD("<<<listChapLiveData " + BaseApplication.gson.toJson(listChap))
                    btGithub.visibility = View.VISIBLE
                }
            )
        }
    }
}
