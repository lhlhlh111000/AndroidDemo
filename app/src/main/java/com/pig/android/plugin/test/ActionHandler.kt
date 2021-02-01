package com.pig.android.plugin.test

import android.app.Activity
import android.widget.Toast

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/2/1
 */
class ActionHandler {

    companion object {

        fun process(activity: Activity) {
            Toast.makeText(activity, "Main", Toast.LENGTH_SHORT).show()
        }
    }
}