package com.pig.android.demo.case

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pig.android.demo.R

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/31
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detatil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rcvMain = view.findViewById<RecyclerView>(R.id.rcv_main)
        rcvMain.layoutManager = LinearLayoutManager(activity)
        rcvMain.adapter = object : RecyclerView.Adapter<MainViewHolder>() {
            var data: ArrayList<Data> = ArrayList()
            init {
                data.add(Data("CollapsingToolbarLayout", switchToCollapsingToolbarLayout))
                for(i in 1 .. 100) {
                    data.add(Data("Item: $i"))
                }
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder
                    = MainViewHolder(LayoutInflater.from(activity)
                .inflate(R.layout.item_main, parent, false))

            override fun getItemCount(): Int = data.size

            override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
                holder.tvItem?.text = data[position].name
                holder.tvItem?.setOnClickListener {
                    data[position].action()
                }
            }
        }
    }

    var switchToCollapsingToolbarLayout: ()->Unit = {
//        startActivity(Intent(this, CollapsingActivity::class.java))
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvItem: TextView? = itemView.findViewById<TextView>(R.id.tv_main_item);
    }

    data class Data constructor(var name:String = "Item") {

        var action: () -> Unit? = {}

        constructor(name: String, action: () -> Unit):this(name) {
            this.action = action;
        }
    }
}