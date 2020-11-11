//
// Created by Cutie on 2020/11/11.
//
#include "demo.h"

extern "C" JNIEXPORT jstring JNICALL Java_com_pig_android_jni_ANativeUtil_helloFromJni
  (JNIEnv *env, jobject obj) {

    return env->NewStringUTF("Hello from jni.");
}