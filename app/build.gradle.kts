plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)

    defaultConfig {
        applicationId = "com.ipalermo.arch"
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
        testInstrumentationRunner = "com.ipalermo.arch.CustomTestRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            // TODO: b/120517460 shrinkResource can't be used with dynamic-feature at this moment.
            //       Need to ensure the app size has not increased
            setManifestPlaceholders(mapOf("firebase_crashlytics_collection_enabled" to true))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
            setManifestPlaceholders(mapOf("firebase_crashlytics_collection_enabled" to false))
        }
        maybeCreate("staging")
        getByName("staging") {
            initWith(getByName("debug"))
            versionNameSuffix = "-staging"

            // Specifies a sorted list of fallback build types that the
            // plugin should try to use when a dependency does not include a
            // "staging" build type.
            // Used with :test-shared, which doesn't have a staging variant.
            setMatchingFallbacks(listOf("debug"))
        }
    }

    buildFeatures {
        dataBinding = true
    }

    signingConfigs {
        // We need to sign debug builds with a debug key to make firebase auth happy
        getByName("debug") {
            storeFile = file("../debug.keystore")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
        }
    }

    // debug and release variants share the same source dir
    sourceSets {
        getByName("debug") {
            java.srcDir("src/debugRelease/java")
        }
        getByName("release") {
            java.srcDir("src/debugRelease/java")
        }
    }

    lintOptions {
        // Eliminates UnusedResources false positives for resources used in DataBinding layouts
        isCheckGeneratedSources = true
        // Running lint over the debug variant is enough
        isCheckReleaseBuilds = false
        // See lint.xml for rules configuration
    }

    testBuildType = "staging"

    // Required for AR because it includes a library built with Java 8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

//    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
//    implementation 'androidx.core:core-ktx:1.2.0'
//    implementation 'androidx.appcompat:appcompat:1.2.0'
//    implementation 'com.google.android.material:material:1.2.1'
//    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))

//    implementation(project(":shared"))
//    testImplementation(project(":test-shared"))
//    testImplementation(project(":androidTest-shared"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libs.CORE_KTX)

    // UI
    implementation(Libs.ACTIVITY_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.CARDVIEW)
    implementation(Libs.BROWSER)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.DRAWER_LAYOUT)
    implementation(Libs.MATERIAL)
    implementation(Libs.FLEXBOX)
    implementation(Libs.LOTTIE)
    implementation(Libs.INK_PAGE_INDICATOR)

    // Architecture Components
    implementation(Libs.LIFECYCLE_LIVE_DATA_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    testImplementation(Libs.ARCH_TESTING)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_RUNTIME)
    kapt(Libs.ROOM_COMPILER)
    testImplementation(Libs.ROOM_KTX)
    testImplementation(Libs.ROOM_RUNTIME)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_VIEWMODEL)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kapt(Libs.ANDROIDX_HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.ANDROIDX_HILT_COMPILER)

    // Glide
    implementation(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)

    // Fabric and Firebase
    implementation(Libs.FIREBASE_UI_AUTH)
    implementation(Libs.CRASHLYTICS)

    // Date and time API for Java.
    implementation(Libs.THREETENABP)
    testImplementation(Libs.THREETENBP)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Instrumentation tests
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.ESPRESSO_CONTRIB)
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.RUNNER)
    androidTestImplementation(Libs.RULES)

    // Local unit tests
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.MOCKITO_CORE)
    testImplementation(Libs.MOCKITO_KOTLIN)
    testImplementation(Libs.HAMCREST)

    // Solve conflicts with gson. DataBinding is using an old version.
    implementation(Libs.GSON)

    implementation(Libs.ARCORE)
}