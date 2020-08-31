package com.pig.android.demo.case.proxy.sources

import android.content.Context

interface  KHelloAction {

    @KHello(hintText = "hello from xxxx")
    fun helloKotlin(context: Context): Boolean
}