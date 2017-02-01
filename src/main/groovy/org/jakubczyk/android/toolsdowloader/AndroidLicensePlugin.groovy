package org.jakubczyk.android.toolsdowloader

import org.apache.commons.io.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLicensePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        File sdkDir

        if (System.getenv("ANDROID_HOME")?.length() > 0) {
            sdkDir = new File(System.getenv("ANDROID_HOME"))
        } else {
            println "ANDROID_HOME not defined"
            sdkDir = new File(System.getProperty("user.home"), ".android-sdk")

            def localProperties = new File(project.rootDir, "local.properties")

            if (!localProperties.exists()) {
                println "Create local properties file"
                localProperties << "sdk.dir=${sdkDir.absolutePath}\n"
            } else {
                println "local.properties file is present"
            }
        }

        def licensesDir = new File(sdkDir, "licenses")

        if (licensesDir.listFiles()?.length > 0) {
            println "Licenses are present"
        } else {
            println "Copy license"
            FileUtils.copyDirectory(project.getRootProject().file("licenses"), licensesDir)
        }


    }

}