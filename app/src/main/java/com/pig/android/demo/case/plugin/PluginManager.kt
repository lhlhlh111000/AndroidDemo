package com.pig.android.demo.case.plugin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.pig.android.plugin.Plugin

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
class PluginManager {

    companion object {
        val INSTANCE = PluginManager()

        const val PLUGIN_CLASS_NAME = "com.pig.android.plugin.test.TestPlugin"
    }

    private var plugin: Plugin? = null

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {
                plugin?.onPause(p0)
            }

            override fun onActivityStarted(p0: Activity) {
                plugin?.onStart(p0)
            }

            override fun onActivityDestroyed(p0: Activity) {
                plugin?.onDestroy(p0)
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(p0: Activity) {
                plugin?.onStop(p0)
            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                plugin?.onCreate(p0)
            }

            override fun onActivityResumed(p0: Activity) {
                plugin?.onResume(p0)
            }
        })

        val pluginTest = InstanceUtil.newInstance(PLUGIN_CLASS_NAME)
        pluginTest?.let {
            plugin = pluginTest as Plugin
        }
    }

    fun callAction(activity: Activity) {
        plugin?.action(activity)
    }
}