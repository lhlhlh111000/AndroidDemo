package com.pig.android.demo

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

private const val FAKE_STRING = "Hello world"

@RunWith(RobolectricTestRunner::class)
@Config(application = App::class, sdk = [Build.VERSION_CODES.M])
class ContextTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun getString() {
        val str = context.getString(R.string.test_str)
        Assert.assertEquals(str, FAKE_STRING)
    }
}