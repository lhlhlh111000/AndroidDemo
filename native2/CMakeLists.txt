# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add_library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

#set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/output/libs/${CMAKE_BUILD_TYPE}/${ANDROID_ABI})

add_library(static-lib
            STATIC
            src/main/cpp/test_static.cpp)

add_library(use-static-lib
            SHARED
            src/main/cpp/use_static_case.cpp)

find_library(log-lib
             log)

# Links your native library against one or more other native libraries.
target_link_libraries(use-static-lib
                      static-lib
                      ${log-lib})