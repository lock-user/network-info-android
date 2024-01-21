import java.util.Properties

plugins {
    `android-application`
    `kotlin-android`
    `kotlin-kapt`
    `android-navigation`
}

android {
    namespace = Config.appId
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.androidTestInstrumentation
    }

    signingConfigs {
        create("keystore") {
            val keystoreProperties = loadKeystore(file("./keystore/keystore.properties"))

            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isZipAlignEnabled = false
            isShrinkResources = false
            aaptOptions.cruncherEnabled = false

            versionNameSuffix = "-dev"
            applicationIdSuffix = ".dev"
            signingConfig = signingConfigs.getByName("keystore")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isZipAlignEnabled = true
            isShrinkResources = true
            aaptOptions.cruncherEnabled = true

            signingConfig = signingConfigs.getByName("keystore")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

fun loadKeystore(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}

dependencies {

    `ktx`
    `appcompat`
    `material`
    `constraint-layout`

    `coroutines-android`
    `coroutines-rx3`

    `koin-core`
    `koin-android`
    `koin-test`

    `leakcanary`

    `lifecycle-livedata`
    `lifecycle-runtime`
    `lifecycle-viewmodel`
    `lifecycle-viewmodel-savedstate`
    `lifecycle-common`

    `square-logcat`

    `navigation-fragment`
    `navigation-runtime`
    `navigation-ui`

    `junit`
    `android-junit`
    `espresso`

}