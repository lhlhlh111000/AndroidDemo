package com.pig.android.demo.case.plugin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_button.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/2/1
 */
class PluginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_click.setOnClickListener {
            activity?.let {
                PluginManager.INSTANCE.callAction(it)
            }
        }
    }
}