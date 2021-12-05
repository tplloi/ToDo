package com.loitp.app

import com.annotation.LogTag
import com.core.base.BaseApplication
import com.core.common.Constants
import com.core.utilities.LUIUtil
import com.data.ActivityData

@LogTag("LApplication")
class LApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        // config activity transition default
        ActivityData.instance.type = Constants.TYPE_ACTIVITY_TRANSITION_SLIDELEFT
        // config font
        LUIUtil.fontForAll = Constants.FONT_PATH
    }
}
