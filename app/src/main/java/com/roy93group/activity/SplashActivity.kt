package com.roy93group.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.openBrowserPolicy
import com.loitp.core.ext.setDelay
import com.roy93group.BuildConfig
import com.roy93group.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@SuppressLint("CustomSplashScreen")
@LogTag("SplashActivity")
@IsFullScreen(false)
class SplashActivity : BaseActivityFont() {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_splash
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        setDelay(
            mls = 1500,
            runnable = {
                goToHome()
            }
        )
        tvVersion.text = "Version ${BuildConfig.VERSION_NAME}"
        tvPolicy.setOnClickListener {
            this.openBrowserPolicy()
        }
    }

    private fun goToHome() {
        launchActivity(MainActivity::class.java)
        this.finishAfterTransition()
    }
}
