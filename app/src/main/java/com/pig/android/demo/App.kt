package com.pig.android.demo

import android.app.Application
import com.pig.android.demo.case.plugin.PluginManager

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()


        PluginManager.INSTANCE.init(this)
    }
}