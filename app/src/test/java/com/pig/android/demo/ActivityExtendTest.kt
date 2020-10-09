package com.pig.android.demo

import android.os.Build
import com.pig.android.demo.extends.go
import com.pig.android.demo.extends.goFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/9
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = App::class, sdk = [Build.VERSION_CODES.O_MR1])
class ActivityExtendTest {

    @Test
    fun goActivity() {
        val activity = mock(TActivity::class.java)
        activity.go<TActivity>()
        verify(activity).go<TActivity>()
    }

    @Test
    fun goFragment() {
        val activity = mock(TActivity::class.java)
        activity.goFragment<TFragment>()
        verify(activity).goFragment<TFragment>()
    }
}