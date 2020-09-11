package com.pig.android.demo.case.proxy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import com.pig.android.demo.case.proxy.sources.KHello
import com.pig.android.demo.case.proxy.sources.KHelloAction
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/31
 */
class ProxyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_proxy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_proxy).setOnClickListener {
            val kHelloActionClass = KHelloAction::class.java
            val kHelloAction = Proxy.newProxyInstance(
                kHelloActionClass.classLoader,
                arrayOf(kHelloActionClass),
                KHelloHandler()) as KHelloAction
            activity?.let {
                kHelloAction.helloKotlin(it)
            }
        }
    }

    class KHelloHandler: InvocationHandler {

        override fun invoke(proxy: Any?, method: Method, args: Array<Any>?): Any {
            val kHello = method.getAnnotation(KHello::class.java) ?: return false
            val context = getObjectFromMethodParam(Context::class.java, args) ?: return false

            Toast.makeText(context, kHello.hintText, Toast.LENGTH_SHORT).show()

            return true
        }

        private fun<T> getObjectFromMethodParam(expectedClass: Class<T>, args: Array<Any>?): T? {
            args ?: return null
            var expectedObject: T? = null
            for(param in args) {
                if(expectedClass.isAssignableFrom(param.javaClass)) {
                    expectedObject = param as T
                }
            }
            return expectedObject
        }
    }
}