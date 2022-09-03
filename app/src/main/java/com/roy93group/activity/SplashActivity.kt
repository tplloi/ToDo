package com.roy93group.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.roy93group.BuildConfig
import com.loitpcore.annotation.IsFullScreen
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LActivityUtil
import com.loitpcore.core.utilities.LSocialUtil
import com.loitpcore.core.utilities.LUIUtil
import com.roy93group.R
import kotlinx.android.synthetic.main.activity_splash.*

@LogTag("SplashActivity")
@IsFullScreen(false)
class SplashActivity : BaseFontActivity() {

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
        LUIUtil.setDelay(
            mls = 1500,
            runnable = {
                goToHome()
            }
        )
        textViewVersion.text = "Version ${BuildConfig.VERSION_NAME}"
        tvPolicy.setOnClickListener {
            LSocialUtil.openBrowserPolicy(context = this)
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        LActivityUtil.tranIn(this)
        this.finishAfterTransition()
    }
}
