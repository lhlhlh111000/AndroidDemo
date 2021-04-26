package com.pig.android.demo.case.plugin

import com.pig.android.demo.App
import dalvik.system.DexClassLoader
import java.io.File

class InterceptClassLoader : DexClassLoader {

    private val cacheClass = HashMap<String, Class<*>>()

    constructor(parent: ClassLoader) : super(
        App.INSTANCE.cacheDir.absolutePath + File.separator + "plugin_test_2_dx_2.jar",
        App.INSTANCE.cacheDir.absolutePath,
        null,
        parent
    )

    override fun loadClass(name: String?): Class<*> {
        name?.let {
            if(it.contains("ActionHandler") || it.contains("TestPlugin")) {
                return internalLoadClass(name)
            }
        }
        return super.loadClass(name)
    }

    override fun loadClass(name: String?, resolve: Boolean): Class<*> {
        name?.let {
            if(it.contains("ActionHandler") || it.contains("TestPlugin")) {
                return internalLoadClass(name)
            }
        }
        return super.loadClass(name, resolve)
    }

    private fun internalLoadClass(className: String) : Class<*> {
        if(cacheClass.containsKey(className)) {
            return cacheClass[className]!!
        }


        val dexClassLoader = DexClassLoader(App.INSTANCE.cacheDir.absolutePath + File.separator + "plugin_test_2_dx_2.jar",
            App.INSTANCE.cacheDir.absolutePath,
            null,
            App.INSTANCE.classLoader)
        val herosClass = dexClassLoader::class.java.superclass
        val m1 = herosClass.getDeclaredMethod("findClass", String::class.java)
        m1.isAccessible = true

        val clazz = m1.invoke(dexClassLoader, className) as Class<*>
        cacheClass[className] = clazz
        return clazz;

//        return dexClassLoader.loadClass(className)
    }
}