plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object GradleVersions {
    const val kotlinVersion = "1.4.0"
    const val gradleVersion = "4.0.1"
}

dependencies {
    implementation("com.android.tools.build:gradle:${GradleVersions.gradleVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${GradleVersions.kotlinVersion}")
    implementation(kotlin("stdlib-jdk8"))
}