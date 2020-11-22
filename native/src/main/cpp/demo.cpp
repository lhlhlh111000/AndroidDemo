//
// Created by Cutie on 2020/11/11.
//
#include "demo.h"
#include "test_static.h"

extern "C" JNIEXPORT jstring JNICALL Java_com_pig_android_jni_ANativeUtil_helloFromJni
  (JNIEnv *env, jobject obj) {

    return env->NewStringUTF("Hello from jni.");
}

extern "C" JNIEXPORT jint JNICALL Java_com_pig_android_jni_ANativeUtil_staticNum
  (JNIEnv *env, jobject obj) {

    return a();
}