package com.pig.android.plugin.test

import android.app.Activity
import android.app.Application
import android.util.Log
import com.pig.android.plugin.Plugin

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
class TestPlugin2 : Plugin {

    companion object {
        val TAG = TestPlugin2::class.java.simpleName
    }

    override fun initApplication(application: Application) {
        Log.e(TAG, "initApplication")
    }

    override fun onCreate(activity: Activity) {
        Log.e(TAG, "onCreate")
    }

    override fun onResume(activity: Activity) {
        Log.e(TAG, "onResume")
    }

    override fun onStart(activity: Activity) {
        Log.e(TAG, "onStart")
    }

    override fun onStop(activity: Activity) {
        Log.e(TAG, "onStop")
    }

    override fun onPause(activity: Activity) {
        Log.e(TAG, "onPause")
    }

    override fun onDestroy(activity: Activity) {
        Log.e(TAG, "onDestroy")
    }

    override fun action(activity: Activity) {
//        ActionHandler.process(activity)

        ActionHandler2.process(activity)
    }
}