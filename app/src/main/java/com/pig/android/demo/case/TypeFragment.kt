package com.pig.android.demo.case

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/28
 */
class TypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val clazz: Class<Animal<Any>> = Baby::class.java as Class<Animal<Any>>
        clazz.newInstance().eat(Apple())
    }
}

interface Animal<in T> {

    fun walk()

    fun eat(food: T)
}

class Apple

class Baby : Animal<Apple> {

    override fun walk() {
    }

    override fun eat(food: Apple) {
        Log.e(TypeFragment::class.java.simpleName, food::class.java.simpleName)
    }
}