package com.pig.android.demo.case.coroutine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_button.*
import kotlinx.coroutines.*

public class CoroutineFragment : Fragment() {

    companion object {
        val TAG: String = CoroutineFragment.javaClass.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_click.setOnClickListener {
            btn_click.isEnabled = false
            globalCoroutine()
        }

        btn_click_2.setOnClickListener {
            btn_click_2.isEnabled = false
            coroutineScope()
        }
    }

    override fun onDestroy() {
        uiScope?.let {
            it.cancel()
            Log.d(TAG, "Coroutine Scope cancel")
        }

        super.onDestroy()
    }

    private fun globalCoroutine() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                var i=0;
                while (i < 100) {
                    Log.d(TAG, "Global coroutine $i")

                    Thread.sleep(1000)
                    i++
                }

                Log.d(TAG, "Global coroutine finish")
            }
        }
    }

    private var uiScope: CoroutineScope? = null

    private fun coroutineScope() {
        uiScope = CoroutineScope(Dispatchers.Main)
        uiScope?.let {
            it.launch {
                var i=0
                withContext(Dispatchers.IO) {
                    while (i < 20) {
                        Log.d(TAG, "Coroutine Scope $i")

                        Thread.sleep(1000)
                        i++
                    }
                }

                Log.d(TAG, "Coroutine Scope finish")
                btn_click_2.text = (btn_click_2.text.toString() + i)
            }
        }
    }
}