buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildDependencies.buildGradle)
        classpath(BuildDependencies.kotlinGradlePlugin)
        classpath(SupportDependencies.hiltDaggerAndroidPlugin)
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}