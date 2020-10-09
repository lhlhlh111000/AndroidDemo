package com.pig.android.demo

import android.os.Build
import com.pig.android.demo.util.TResUtil
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

private const val FAKE_STRING = "Hello world"

@RunWith(RobolectricTestRunner::class)
@Config(application = App::class, sdk = [Build.VERSION_CODES.O_MR1])
class ContextTest {

    @Test
    fun getString() {
        Assert.assertEquals(TResUtil.getString(R.string.test_str), FAKE_STRING)
    }
}