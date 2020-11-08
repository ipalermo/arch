object Versions {
    val versionName = "7.0.15" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    private val versionCodeBase = 70150 // XYYZZM; M = Module (tv, mobile)
    val versionCodeMobile = versionCodeBase + 3

    const val COMPILE_SDK = 30
    const val TARGET_SDK = 30
    const val MIN_SDK = 21

    const val ANDROID_GRADLE_PLUGIN = "4.1.0"
    const val BENCHMARK = "1.0.0"
    const val CRASHLYTICS_GRADLE_PLUGIN = "2.3.0"
    const val GOOGLE_SERVICES = "4.3.3"
    const val KOTLIN = "1.4.10"
    const val NAVIGATION = "2.3.0"
    const val HILT = "2.28-alpha"

    // TODO: Remove this once the version for
    //  "org.threeten:threetenbp:${Versions.threetenbp}:no-tzdb" using java-platform in the
    //  depconstraints/build.gradle.kts is defined
    const val THREETENBP = "1.3.6"
}
