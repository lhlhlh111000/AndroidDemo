package com.pig.android.demo.case

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_button.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class CountDownLatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_click.setOnClickListener {

            val countDownLatch = CountDownLatch(1)
            val service = Executors.newCachedThreadPool()

            service.execute {
                Thread.sleep(10_000)
                countDownLatch.countDown()
            }

            countDownLatch.await()

            val alertDialog = AlertDialog.Builder(activity)
                .setTitle("hello")
                .setMessage("this is content")
                .setPositiveButton("确定") { dialog : DialogInterface, _: Int ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}