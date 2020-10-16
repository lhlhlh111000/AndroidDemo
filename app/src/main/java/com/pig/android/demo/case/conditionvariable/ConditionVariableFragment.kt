package com.pig.android.demo.case.conditionvariable

import android.os.Bundle
import android.os.ConditionVariable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_condition.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/16
 */
class ConditionVariableFragment : Fragment() {

    private lateinit var conditionThread1: ConditionVariable

    private lateinit var conditionThread2: ConditionVariable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_condition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_in_turn.setOnClickListener {
            startInTurnThread()
        }
    }

    private fun startInTurnThread() {
        val thread1 = Thread(Runnable {
            Thread.sleep(3000)
            Log.e(ConditionVariableFragment::class.java.simpleName,
                "Thread 1 log")
            conditionThread2.open()
        })

        Thread(Runnable {
            conditionThread2 = ConditionVariable(false)
            thread1.start()
            conditionThread2.block()
            Log.e(ConditionVariableFragment::class.java.simpleName,
                "Thread 2 log")
        }).start()
    }
}