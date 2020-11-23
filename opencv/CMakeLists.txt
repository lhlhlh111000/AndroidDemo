# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add_library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

# 静态库头文件目录指定
include_directories(include)

add_library(opencv_case
            SHARED
            src/main/cpp/opencv_case.cpp)

# opencv核心库
add_library(opencv_core STATIC IMPORTED)

set_target_properties(opencv_core
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/lib/${ANDROID_ABI}/libopencv_core.a)

# opencv纹理处理库
add_library(opencv_imgproc STATIC IMPORTED)

set_target_properties(opencv_imgproc
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/lib/${ANDROID_ABI}/libopencv_imgproc.a)


find_library(log-lib
             log)

# Links your native library against one or more other native libraries.
target_link_libraries(opencv_case
                      opencv_core
                      opencv_imgproc
                      ${log-lib})