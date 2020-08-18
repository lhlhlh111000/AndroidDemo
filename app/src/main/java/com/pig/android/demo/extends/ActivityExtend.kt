package com.pig.android.demo.extends

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/18
 */
inline fun <reified T : Activity> Activity.go(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}