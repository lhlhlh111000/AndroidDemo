package com.pig.android.demo.case.delegation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_button.*

class DelegateTestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ListWithTrash<Person>()
        val person = Person("LI", "XIAOLONG")
        list.add(person)

        btn_click.setOnClickListener {
            list.remove(person)

            Toast.makeText(activity, list.size.toString(), Toast.LENGTH_SHORT).show()
        }

        btn_click_2.setOnClickListener {
            val recoverItem = list.recover()

            recoverItem?.name = "ZHANG"
            recoverItem?.lastName = "XIAOLONG"

            recoverItem?.let {
                val sb = StringBuilder()
                sb.append("name: ").append(it.name).append("\n")
                    .append("lastName: ").append(it.lastName).append("\n")
                    .append("updateCount: ").append(it.updateCount)

                Toast.makeText(activity, sb.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}