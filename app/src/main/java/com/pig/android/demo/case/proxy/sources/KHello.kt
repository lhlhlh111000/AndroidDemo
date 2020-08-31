package com.pig.android.demo.case.proxy.sources

@Retention
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
annotation class KHello (
        val hintText: String = "Hello from Annotation"
)