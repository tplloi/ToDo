package com.roy93group.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseFragment
import com.loitp.core.ext.isDarkTheme
import com.loitp.core.ext.setDarkTheme
import com.loitp.core.ext.showDialog2
import com.loitp.core.ext.transActivityNoAnimation
import com.roy93group.R
import com.roy93group.activity.MainActivity
import kotlinx.android.synthetic.main.frm_setting.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("SettingFragment")
class SettingFragment : BaseFragment() {
    private var dialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModels()
    }

    override fun onDestroyView() {
        dialog?.dismiss()
        super.onDestroyView()
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.frm_setting
    }

    private fun setupViews() {
        val isDarkTheme = context?.isDarkTheme() ?: false
        swEnableDarkMode.isChecked = isDarkTheme

        swEnableDarkMode.setOnCheckedChangeListener { _, isChecked ->
            handleSwitchDarkTheme(isChecked = isChecked)
        }
    }

    private fun setupViewModels() {
    }

    private fun handleSwitchDarkTheme(isChecked: Boolean) {
        context?.let { c ->
            val isDarkTheme = c.isDarkTheme()
            if (isDarkTheme == isChecked) {
                return@let
            }

            dialog = c.showDialog2(
                title = getString(R.string.warning),
                msg = getString(R.string.this_app_will_be_restarted),
                button1 = getString(R.string.cancel),
                button2 = getString(R.string.ok),
                onClickButton1 = {
                    swEnableDarkMode?.isChecked = c.isDarkTheme()
                },
                onClickButton2 = {
                    if (isChecked) {
                        c.setDarkTheme(isDarkTheme = true)
                    } else {
                        c.setDarkTheme(isDarkTheme = false)
                    }

                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    c.transActivityNoAnimation()

                    dialog?.dismiss()
                }
            )
            dialog?.setOnCancelListener {
                swEnableDarkMode?.isChecked = c.isDarkTheme()
            }
        }
    }
}
