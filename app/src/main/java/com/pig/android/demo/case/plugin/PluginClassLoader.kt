package com.pig.android.demo.case.plugin

import com.pig.android.demo.App
import dalvik.system.DexClassLoader
import java.io.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
class PluginClassLoader {

    companion object {
        val INSTANCE = PluginClassLoader()
    }

    fun createClassLoader(fileName: String) : DexClassLoader? {
        val cacheFile = App.INSTANCE.cacheDir
        val jarFilePath = cacheFile.absolutePath + File.separator + fileName
        val jarFile = File(jarFilePath)

        if(!jarFile.exists()) {
            jarFile.createNewFile()
        }

        try {
            copyFiles(fileName, jarFile)
            return DexClassLoader(jarFilePath,
                cacheFile.absolutePath,
                null, this::class.java.classLoader)
        }catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    fun createClassLoader() : DexClassLoader? {
        return createClassLoader("plugin_test_dx.jar")
    }

    private fun copyFiles(
        fileName: String,
        desFile: File
    ) {
        var ins: InputStream? = null
        var out: OutputStream? = null
        try {
            ins = App.INSTANCE.assets.open(fileName)
            out = FileOutputStream(desFile.absolutePath)
            val bytes = ByteArray(1024)
            var i: Int
            while (ins.read(bytes).also { i = it } != -1) out.write(bytes, 0, i)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                ins?.close()
                out?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}