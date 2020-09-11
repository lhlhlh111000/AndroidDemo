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

        private const val PLUGIN_CLASS_NAME = "com.pig.android.plugin.test.TestPlugin"
    }

    private val plugins = ArrayList<Plugin>()

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {
                plugins.forEach{
                    it.onPause(p0)
                }
            }

            override fun onActivityStarted(p0: Activity) {
                plugins.forEach{
                    it.onStart(p0)
                }
            }

            override fun onActivityDestroyed(p0: Activity) {
                plugins.forEach{
                    it.onDestroy(p0)
                }
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(p0: Activity) {
                plugins.forEach{
                    it.onStop(p0)
                }
            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                plugins.forEach{
                    it.onCreate(p0)
                }
            }

            override fun onActivityResumed(p0: Activity) {
                plugins.forEach{
                    it.onResume(p0)
                }
            }
        })

        val pluginTest = InstanceUtil.newInstance(PLUGIN_CLASS_NAME)
        pluginTest?.let {
            plugins.add(it as Plugin)
        }
    }
}