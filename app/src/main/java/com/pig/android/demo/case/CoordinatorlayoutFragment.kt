package com.pig.android.demo.case

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import com.pig.android.demo.extends.goFragment
import kotlinx.android.synthetic.main.fragment_coordinator.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/31
 */
class CoordinatorlayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coordinator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_collapsing.setOnClickListener {
            activity?.goFragment<CollapsingFragment>()
        }

        btn_detail.setOnClickListener {
            activity?.goFragment<DetailFragment>()
        }
    }
}