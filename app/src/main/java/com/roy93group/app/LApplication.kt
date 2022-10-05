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

//TODO update right drawer -> show quote random
//TODO search task
//TODO sort task
//TODO color task
//TODO khi bat dark mode, luc tao task 2 cai text input layout color cua label bi sai
//TODO type list, grid, short or detail
//TODO task detail, chuyen nut delete vao trong screen detail
//TODO man hinh setting cho custom type task, add type task vao left drawar
//TODO left drawer cho them 1 cai mark (fav) task

//DONE
//change pkg name
//ic_launcher
//add new keystore 2022.09.05
//rate app
//add bkg animation splash

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
