package com.pig.android.jni

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/11/11
 */
class ANativeUtil {

    companion object {
        init {
            System.loadLibrary("demo")
        }
    }

    external fun helloFromJni(): String

    external fun staticNum(): Int
}