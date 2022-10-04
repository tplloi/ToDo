package com.roy93group.app

import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseApplication
import com.loitpcore.core.common.Constants
import com.loitpcore.core.utilities.LUIUtil
import com.loitpcore.data.ActivityData
import com.roy93group.db.TaskDatabase

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */

//TODO rate app
//TODO add bkg animation splash
//TODO search task
//TODO sort task
//TODO color task
//TODO update right drawer
//TODO khi bat dark mode, luc tao task 2 cai text input layout color cua label bi sai

//DONE
//change pkg name
//ic_launcher
//add new keystore 2022.09.05
@LogTag("LApplication")
class LApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        setupApp()
    }

    private fun setupApp() {
        ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SLIDE_LEFT
        LUIUtil.fontForAll = Constants.FONT_PATH
        TaskDatabase.getInstance(this)
    }
}
