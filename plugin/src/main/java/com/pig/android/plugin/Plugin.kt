package com.pig.android.plugin

import android.app.Activity
import android.app.Application

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
interface Plugin {

    fun initApplication(application: Application)

    fun onCreate(activity: Activity)

    fun onResume(activity: Activity)

    fun onStart(activity: Activity)

    fun onStop(activity: Activity)

    fun onPause(activity: Activity)

    fun onDestroy(activity: Activity)
}