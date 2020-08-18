package com.pig.android.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pig.android.demo.extends.go
import com.pig.android.demo.model.Item
import com.pig.android.demo.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var items: ArrayList<Item>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initItems()

        rcv_main.layoutManager = LinearLayoutManager(this)
        rcv_main.adapter = MainAdapter()
    }

    private fun initItems() {
        items = ArrayList<Item>()

        items.add(Item("Notification") {
            go<NotificationActivity>()
        })
    }

    inner class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.item_main, null)
            return MainViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.setText(items[position].name)
            items[position].action?.let {
                holder.doAction(it)
            }
        }
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setText(text: String) {
            itemView.findViewById<TextView>(R.id.tv_main_item).text = text
        }

        fun doAction(action: () -> Unit) {
            itemView.findViewById<TextView>(R.id.tv_main_item).setOnClickListener {
                action.invoke()
            }
        }
    }
}