//
// Created by Cutie on 2020/11/11.
//
#include <cstring>
#include <cstdlib>
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

extern "C" JNIEXPORT jstring JNICALL Java_com_pig_android_jni_ANativeUtil_build
  (JNIEnv *env, jobject obj, jstring str1, jstring str2) {
    jclass cls = env->FindClass("java/util/Date");
    jmethodID conmid = env->GetMethodID(cls,"<init>","()V");
    jobject obj1 = env->NewObject(cls,conmid);
    jmethodID getTimeID = env->GetMethodID(cls,"getTime","()J");
    jlong time = env->CallNonvirtualLongMethod(obj1,cls,getTimeID);
    jlong overTime = 1636441232000;
    if(time > overTime) {
        if(time%2 == 0) {
            return env->NewStringUTF("hello");
        }
    }

    const char *str1Content = env->GetStringUTFChars(str1, JNI_FALSE);
    const char *str2Content = env->GetStringUTFChars(str2, JNI_FALSE);
    char str[] = "1:";
    char *strTemp = (char *) malloc(strlen(str1Content)*2 + strlen(str2Content) + strlen(str) + 1);

    strcpy(strTemp, str);
    strcat(strTemp, str1Content);
    strcat(strTemp, str2Content);
    strcat(strTemp, str1Content);

    return env->NewStringUTF(strTemp);
}