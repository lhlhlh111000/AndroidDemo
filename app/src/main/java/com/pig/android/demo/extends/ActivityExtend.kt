package com.pig.android.demo.extends

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pig.android.demo.ContainerActivity
import com.pig.android.demo.FullContainerActivity

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

inline fun <reified T : Fragment> Activity.goFragment(bundle: Bundle? = null) {
    val intent = Intent(this, ContainerActivity::class.java)
    intent.putExtra(ContainerActivity.FRAGMENT_NAME, T::class.qualifiedName)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}

inline fun <reified T : Fragment> Activity.goFullFragment(bundle: Bundle? = null) {
    val intent = Intent(this, FullContainerActivity::class.java)
    intent.putExtra(ContainerActivity.FRAGMENT_NAME, T::class.qualifiedName)
    bundle?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}