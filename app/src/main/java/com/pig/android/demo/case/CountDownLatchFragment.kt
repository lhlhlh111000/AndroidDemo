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

        btn_click_2.setOnClickListener {
            val service = Executors.newCachedThreadPool()
            val countDownLatch1 = CountDownLatch(1)
            val countDownLatch2 = CountDownLatch(1)
            val countDownLatch3 = CountDownLatch(1)

            val runnable1 = Runnable {
                countDownLatch1.await()
                activity?.runOnUiThread{
                    AlertDialog.Builder(activity)
                        .setTitle("hello1")
                        .setMessage("this is content")
                        .setPositiveButton("确定") { dialog : DialogInterface, _: Int ->
                            dialog.dismiss()
                            countDownLatch2.countDown()
                        }
                        .show()
                }
            }

            val runnable2 = Runnable {
                countDownLatch2.await()
                activity?.runOnUiThread{
                    AlertDialog.Builder(activity)
                        .setTitle("hello2")
                        .setMessage("this is content")
                        .setPositiveButton("确定") { dialog : DialogInterface, _: Int ->
                            dialog.dismiss()
                            countDownLatch3.countDown()
                        }
                        .show()
                }
            }

            val runnable3 = Runnable {
                countDownLatch3.await()
                activity?.runOnUiThread{
                    AlertDialog.Builder(activity)
                        .setTitle("hello3")
                        .setMessage("this is content")
                        .setPositiveButton("确定") { dialog : DialogInterface, _: Int ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }

            service.execute(runnable1)
            service.execute(runnable2)
            service.execute(runnable3)

            AlertDialog.Builder(activity)
                .setTitle("hello")
                .setMessage("this is content")
                .setPositiveButton("确定") { dialog : DialogInterface, _: Int ->
                    dialog.dismiss()
                    countDownLatch1.countDown()
                }
                .show()
        }
    }
}