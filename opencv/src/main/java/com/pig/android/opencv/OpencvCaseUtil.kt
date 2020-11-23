package com.pig.android.opencv

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/11/23
 */
class OpencvCaseUtil {

    companion object {
        init {
            System.loadLibrary("opencv_case")
        }
    }

    external fun gray(data: ByteArray, width: Int, height: Int): ByteArray
}