package com.pig.android.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/31
 */
open class ContainerActivity : AppCompatActivity() {

    companion object{
        public const val FRAGMENT_NAME = "fragment_name"
    }

    private lateinit var fragmentName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        intent.getStringExtra(FRAGMENT_NAME)?.let {
            fragmentName = it
            val fm = this.supportFragmentManager
            val ft = fm.beginTransaction()
            val fragment = fm.fragmentFactory.instantiate(classLoader, fragmentName)
            this.intent.extras?.let { bundle ->
                fragment.arguments = bundle
            }
            ft.replace(R.id.frl_container, fragment, fragmentName)
            ft.commit()
        }
    }
}