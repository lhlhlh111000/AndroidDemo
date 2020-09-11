package com.pig.android.demo.case.plugin

import java.lang.reflect.InvocationTargetException

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/9/11
 */
class InstanceUtil {

    companion object {

        fun newInstance(className: String): Any? {
            try {
                val clazz = Class.forName(className)
                val constructor = clazz.getConstructor()
//                val method = clazz.getDeclaredMethod("getInstance")
//                return method.invoke(null)
                return constructor.newInstance()
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
}