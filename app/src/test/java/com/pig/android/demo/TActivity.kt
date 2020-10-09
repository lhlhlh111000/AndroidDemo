package com.pig.android.demo

import android.app.Activity
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
class TActivity : Activity() {


    override fun getPackageName(): String {
        return ApplicationProvider.getApplicationContext<Context>().opPackageName
    }
}