package com.pig.android.demo

import android.content.Intent
import android.os.Build
import androidx.test.core.app.ActivityScenario
import com.pig.android.demo.case.InstallAppFragment
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
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
class ContainerActivityTest {

    private lateinit var scenario: ActivityScenario<ContainerActivity>

    @Before
    fun setup() {
        val context = TActivity()
        val intent = Intent(context, ContainerActivity::class.java)
        intent.putExtra(ContainerActivity.FRAGMENT_NAME, InstallAppFragment::class.qualifiedName)
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun fragmentNotNull() {
        scenario.onActivity {
            assertNotNull(it.supportFragmentManager.findFragmentById(R.id.frl_container))
        }
    }
}