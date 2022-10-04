package com.roy93group.model

import androidx.annotation.Keep
import org.greenrobot.eventbus.EventBus

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@Keep
class MessageEvent : java.io.Serializable {
    var msg: String? = null

    companion object {
        const val CREATE_TASK = "CREATE_TASK"
        const val UPDATE_TASK = "UPDATE_TASK"
        const val DELETE_TASK = "DELETE_TASK"

        fun postMsg(msg: String) {
            val messageEvent = MessageEvent().apply {
                this.msg = msg
            }
            EventBus.getDefault().post(messageEvent)
        }
    }
}
