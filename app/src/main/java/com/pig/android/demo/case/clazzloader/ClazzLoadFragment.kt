package com.pig.android.demo.case.clazzloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import com.pig.android.demo.extends.goFragment
import kotlinx.android.synthetic.main.fragment_clazz_load.*
import java.lang.reflect.Field

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/1/29
 */
class ClazzLoadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_clazz_load, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_load_result.setOnClickListener {
            activity?.goFragment<ClazzResultFragment>()
        }

        btn_load_apply.setOnClickListener {
            applyRef()
        }
    }

    private fun applyRef() {
        var loader = javaClass.classLoader

        var clazz = loader.loadClass("com.pig.android.demo.case.clazzloader.ClazzResultFragment")
        var field: Field? = null
        clazz.declaredFields.forEach {
            if(it.name == "textPrinter") {
                field = it
            }
        }

        var bClazz = loader.loadClass("com.pig.android.demo.case.clazzloader.BTextPrinter")
        var b = bClazz.newInstance()

        field?.let {
            it.isAccessible = true
            it.set(null, b)
        }
    }
}