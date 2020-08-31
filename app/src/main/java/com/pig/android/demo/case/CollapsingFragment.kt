package com.pig.android.demo.case

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pig.android.demo.MainActivity
import com.pig.android.demo.R

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/31
 */
class CollapsingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collapsing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcvCollapsing = view.findViewById<RecyclerView>(R.id.rcv_collapsing)
        rcvCollapsing.layoutManager = LinearLayoutManager(activity)
        rcvCollapsing.adapter = object : RecyclerView.Adapter<MainActivity.MainViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): MainActivity.MainViewHolder = MainActivity.MainViewHolder(
                LayoutInflater.from(activity).inflate(R.layout.item_main, parent, false)
            )

            override fun getItemCount(): Int = 20

            override fun onBindViewHolder(holder: MainActivity.MainViewHolder, position: Int) {
                holder.setText("Item: $position")
            }
        }
    }
}