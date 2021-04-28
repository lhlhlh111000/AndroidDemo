package com.pig.android.plugin.PigDemo

import com.pig.android.plugin.PigDemo.library.LibraryProcessPlugin
import com.pig.android.plugin.PigDemo.main.MainProcessPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class PigDemoPlugin implements Plugin<Project> {

    private Plugin mPluginHandler

    @Override
    void apply(Project project) {
        boolean isLibrary = project.getPlugins().hasPlugin("com.android.library")

        if(isLibrary) {
            mPluginHandler = new LibraryProcessPlugin()
        }else {
            mPluginHandler = new MainProcessPlugin()
        }
        if(mPluginHandler != null) {
            mPluginHandler.apply(project)
        }

        project.getTasks().iterator().each {
            println(it.name)
        }
    }
}