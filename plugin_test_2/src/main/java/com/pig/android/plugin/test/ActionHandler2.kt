package com.pig.android.plugin.test

import android.app.Activity
import androidx.appcompat.app.AlertDialog

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/2/1
 */
class ActionHandler2 {

    companion object {

        fun process(activity: Activity) {
            AlertDialog.Builder(activity)
                .setTitle("这是标题啊")
                .setMessage("这是提示内容啊！")
                .show()
        }
    }
}