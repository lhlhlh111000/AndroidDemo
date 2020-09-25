package com.pig.android.demo

import android.app.Application
import com.pig.android.demo.case.plugin.PluginManager
import com.pig.android.demo.channel.Channel

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
class App : Application() {

    companion object {
        lateinit var INSTANCE : App
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        PluginManager.INSTANCE.init(this)
        Channel.init(this)
    }
}