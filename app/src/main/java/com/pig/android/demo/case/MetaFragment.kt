package com.pig.android.demo.case

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pig.android.demo.R

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/8
 */
class MetaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_info)
            .text = metaValue()
    }

    private fun metaValue(): String {
        val channel = activity?.let {
            val activityInfo = it.packageManager.getApplicationInfo(it.packageName,
                PackageManager.GET_META_DATA)
            activityInfo.metaData.getString("channel")
        }

        return channel ?: "Empty"
    }
}