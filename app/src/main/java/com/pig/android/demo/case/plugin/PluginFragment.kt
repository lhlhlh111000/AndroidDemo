package com.pig.android.demo.case.plugin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.App
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_button.*
import java.lang.reflect.InvocationTargetException

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/2/1
 */
class PluginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_click.setOnClickListener {
            activity?.let {
                PluginManager.INSTANCE.callAction(it)
            }
        }

        btn_click_2.setOnClickListener {
//            applyFunc()


            PluginClassLoader.INSTANCE.setupInterceptClassLoader()

            val pluginTest = newInstance("com.pig.android.plugin.test.TestPlugin2")

            val pluginManagerClazz = PluginManager::class.java
            val field = pluginManagerClazz.getDeclaredField("plugin")
            field.isAccessible = true
            field.set(PluginManager.INSTANCE, pluginTest)
        }
    }

    private fun applyFunc() {
        val clazzLoader = PluginClassLoader.INSTANCE.createClassLoader("plugin_test_2_dx_2.jar")
        clazzLoader?.let {
            val pluginTest = InstanceUtil.newInstance(PluginManager.PLUGIN_CLASS_NAME, it)

            val pluginManagerClazz = PluginManager::class.java
            val field = pluginManagerClazz.getDeclaredField("plugin")
            field.isAccessible = true
            field.set(PluginManager.INSTANCE, pluginTest)
        }
    }

    private fun newInstance(className: String): Any? {
        try {
            val classLoader = App.INSTANCE.classLoader
            val clazz = classLoader?.loadClass(className)
            val constructor = clazz?.let {
                it.getConstructor()
            }
            return constructor?.newInstance()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

        return null
    }
}