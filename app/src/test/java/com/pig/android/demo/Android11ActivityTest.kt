package com.pig.android.demo

import android.os.Build
import androidx.test.core.app.ActivityScenario
import com.pig.android.demo.case.Android11Activity
import kotlinx.android.synthetic.main.activity_android11.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
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
class Android11ActivityTest {

    private lateinit var activity: Android11Activity

    private lateinit var scenario: ActivityScenario<Android11Activity>

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(Android11Activity::class.java)
            .create()
            .resume()
            .get()

        scenario = ActivityScenario.launch(Android11Activity::class.java)
    }

    @Test
    fun getPhoneNumber() {
        activity.btn_phone_number.performClick()

        scenario.onActivity {
            it.btn_phone_number.performClick()
        }
    }
}