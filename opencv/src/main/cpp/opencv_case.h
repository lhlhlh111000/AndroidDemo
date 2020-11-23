//
// Created by Cutie on 2020/11/23.
//
#include <jni.h>
#ifndef ANDROIDDEMO_OPENCV_CASE_H
#define ANDROIDDEMO_OPENCV_CASE_H

extern "C" {
#endif
/*
 * Class:     com_pig_android_jni_ANativeUtil
 * Method:    helloFromJni
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jbyteArray JNICALL Java_com_pig_android_opencv_OpencvCaseUtil_gray
        (JNIEnv *, jobject, jbyteArray, jint, jint);

#ifdef __cplusplus
}

#endif //ANDROIDDEMO_OPENCV_CASE_H
