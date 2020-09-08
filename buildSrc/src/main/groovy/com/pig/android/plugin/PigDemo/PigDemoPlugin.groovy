package com.pig.android.plugin.PigDemo

import org.gradle.api.Plugin
import org.gradle.api.Project

class PigDemoPlugin implements Plugin<Project> {


    private ExtraConfig mExtraConfig;

    @Override
    void apply(Project project) {
        println("hello")
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

        mExtraConfig = project.extensions.create("extraConfig", ExtraConfig)
        project.afterEvaluate {
            String assetsDir = mExtraConfig.assetsPathDir
            if(null != assetsDir) {
                println(assetsDir)
                project.android.sourceSets.main.assets.srcDirs += new File(assetsDir)
            }else {
                println("assets config is null")
            }

            // https://developer.android.com/studio/known-issues
            // https://stackoverflow.com/questions/54551055/unable-to-overwrite-the-manifest-with-gradle-plugin-3-3
            project.getExtensions().android.applicationVariants.all {variant ->
                String variantName = variant.name.capitalize()
                println(variantName)
                def processManifestTask = project.tasks.getByName("process${variantName}Manifest")
                processManifestTask.doLast { pmt ->
//                    String manifestPath = "$pmt.manifestOutputDirectory/AndroidManifest.xml"
                    def manifestPath = new File(processManifestTask.manifestOutputDirectory.get().asFile, "AndroidManifest.xml")
                    def manifest = manifestPath.getText("UTF-8")
                    if(null != mExtraConfig.channelID) {
//                        def xml = new XmlParser().parseText(manifest)
//                        xml.application[0].appendNode("meta-data", ['android:name': 'channel', 'android:value': mExtraConfig.channelID])
//                        def serialize = XmlUtil.serialize(xml)
//                        manifestPath.write(serialize)
                        manifest = manifest.replaceAll("</application>",
                                "<meta-data android:name=\"channel\" android:value=\"$mExtraConfig.channelID\" /></application>")
                        println(manifest.find("</application>"))
                        println(manifest)
                        manifestPath.write(manifest, "UTF-8")
                    }
                }
            }
        }
    }
}