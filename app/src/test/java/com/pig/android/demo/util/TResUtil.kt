package com.pig.android.demo.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/9
 */
class TResUtil {

    companion object{

        fun getString(resId: Int) : String {
            val context = ApplicationProvider.getApplicationContext<Context>()
            return context.getString(resId)
        }
    }
}