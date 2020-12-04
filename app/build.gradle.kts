plugins {
    id(BuildDependencies.androidApplication)
    id(BuildDependencies.kotlinAndroid)
    id(BuildDependencies.kotlinAndroidExtension)
    id(BuildDependencies.kotlinKapt)
    id(BuildDependencies.daggerHiltPlugin)

}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = BuildDependencies.applicationId
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        buildToolsVersion(Versions.buildToolsVersion)

        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = TestingDependencies.jUnitRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    testOptions {
        unitTests {
            it.isIncludeAndroidResources = true
        }
    }

}

dependencies {
    implementation(BuildDependencies.coreKtx)

    implementation(LayoutDependencies.appCompat)
    implementation(LayoutDependencies.constraintLayout)
    implementation(LayoutDependencies.materialComponents)

    implementation(SupportDependencies.room)
    implementation(SupportDependencies.roomKtx)
    implementation(SupportDependencies.activityKtx)
    implementation(SupportDependencies.lifecycleExtensions)
    implementation(SupportDependencies.liveDataExtensions)
    implementation(SupportDependencies.viewModelLifeCycle)
    implementation(SupportDependencies.daggerHilt)
    implementation(SupportDependencies.hiltLifecycleViewModel)
    implementation(SupportDependencies.glide)
    implementation(SupportDependencies.volley)


    kapt(SupportDependencies.roomCompiler)
    kapt(SupportDependencies.hiltAndroidCompiler)
    kapt(SupportDependencies.hiltCompiler)
    kapt(SupportDependencies.glideProcessor)

    testImplementation(TestingDependencies.jUnit)
    testImplementation(TestingDependencies.coroutineTst)
    testImplementation(TestingDependencies.coreTesting)
    testImplementation(TestingDependencies.robolectric)
    testImplementation(TestingDependencies.androidxCoreTesting)

}