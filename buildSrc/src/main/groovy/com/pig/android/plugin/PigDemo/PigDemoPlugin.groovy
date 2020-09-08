package com.pig.android.plugin.PigDemo

import org.gradle.api.Plugin
import org.gradle.api.Project

class PigDemoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.dependencies.add("implementation", "androidx.appcompat:appcompat:1.2.0")
        project.dependencies.add("implementation", "androidx.core:core-ktx:1.3.1")
        project.dependencies.add("implementation", "androidx.constraintlayout:constraintlayout:1.1.3")
        project.dependencies.add("implementation", "androidx.coordinatorlayout:coordinatorlayout:1.1.0")
        project.dependencies.add("implementation", "com.google.android.material:material:1.2.0")
        project.dependencies.add("implementation", "androidx.recyclerview:recyclerview:1.1.0")
        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$project.rootProject.ext.kotlin_version")


        project.dependencies.add("testImplementation", "junit:junit:4.12")
        project.dependencies.add("androidTestImplementation", "androidx.test.ext:junit:1.1.1")
        project.dependencies.add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.2.0")

        println(project.getRootDir().getAbsolutePath())
        File assetsDir = new File(project.getRootDir().getAbsolutePath() + "/tmp/pig/assets")
        project.android.sourceSets.main.assets.srcDirs += assetsDir
    }
}