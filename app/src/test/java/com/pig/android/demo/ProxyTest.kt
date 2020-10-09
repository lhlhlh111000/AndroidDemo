package com.pig.android.demo

import android.content.Intent
import android.os.Build
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import com.pig.android.demo.case.proxy.ProxyFragment
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

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
class ProxyTest {

    private lateinit var scenario: ActivityScenario<ContainerActivity>

    @Before
    fun setup() {
        val context = TActivity()
        val intent = Intent(context, ContainerActivity::class.java)
        intent.putExtra(ContainerActivity.FRAGMENT_NAME, ProxyFragment::class.qualifiedName)
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun showToast() {
        scenario.onActivity {
            val fragment = it.supportFragmentManager.findFragmentById(R.id.frl_container) as ProxyFragment
            fragment.view?.findViewById<Button>(R.id.btn_proxy)?.performClick()
            val toastStr= ShadowToast.getTextOfLatestToast()
            assertEquals(toastStr, "hello from xxxx")
        }
    }
}