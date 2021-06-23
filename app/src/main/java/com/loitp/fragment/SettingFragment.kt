package com.loitp.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.annotation.LogTag
import com.core.base.BaseApplication
import com.core.base.BaseFragment
import com.core.utilities.LSocialUtil
import com.loitp.R
import com.loitp.viewmodels.MainViewModel
import com.views.setSafeOnClickListener
import kotlinx.android.synthetic.main.frm_home.*

@LogTag("SettingFragment")
class SettingFragment : BaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModels()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_setting
    }

    private fun setupViews() {

    }

    private fun setupViewModels() {

    }
}
