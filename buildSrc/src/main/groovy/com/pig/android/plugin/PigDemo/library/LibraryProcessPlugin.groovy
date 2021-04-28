package com.pig.android.plugin.PigDemo.library

import com.android.build.gradle.LibraryExtension
import com.pig.android.plugin.PigDemo.library.trans.PluginRenameTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryProcessPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def makePluginTask =  project.getTasksByName("makeJar", false)
        println("是否需要重命名："  + makePluginTask.isEmpty() ? "不需要" : "需要")
//        if(!makePluginTask.isEmpty()) {
            project.extensions.getByType(LibraryExtension).registerTransform(new PluginRenameTransform(project))
//        }
    }
}