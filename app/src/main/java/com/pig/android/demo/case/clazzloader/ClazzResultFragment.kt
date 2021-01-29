package com.pig.android.demo.case.clazzloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_info.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/1/29
 */
class ClazzResultFragment : Fragment() {

    companion object{
        private val textPrinter : TextPrinter = ATextPrinter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_info.text = textPrinter.buildText()
    }
}