plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.afapps.kotlindsl"
    compileSdk = 35

    signingConfigs {
        create("signingConfigRelease") {
            storeFile = file(KeyHelper.getValue(KeyHelper.KEY_STORE_FILE))
            storePassword = KeyHelper.getValue(KeyHelper.KEY_STORE_PASS)
            keyAlias = KeyHelper.getValue(KeyHelper.KEY_ALIAS)
            keyPassword = KeyHelper.getValue(KeyHelper.KEY_PASS)
        }
    }

    defaultConfig {
        applicationId = "com.afapps.kotlindsl"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        signingConfig = signingConfigs.getByName("signingConfigRelease")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions("appType")
    productFlavors {
        create("_dev") {
            dimension = "appType"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            addManifestPlaceholders(
                mapOf(
                    "appIcon" to "@mipmap/ic_launcher_prod",
                    "appIconRound" to "@mipmap/ic_launcher_prod_round"
                )
            )
            resValue("string", "app_name", "KotlinDslExample$versionNameSuffix")
            buildConfigField("String", "ONESIGNAL_APP_ID", "\"${KeyHelper.KEY_ONESIGNAL_APP_ID_DEV}\"")
            buildConfigField("String", "SERVER_URL", "\"${KeyHelper.KEY_SERVER_URL_DEV}\"")

        }
        create("_test") {
            dimension = "appType"
            applicationIdSuffix = ".test"
            versionNameSuffix = "-test"

            addManifestPlaceholders(
                mapOf(
                    "appIcon" to "@mipmap/ic_launcher_test",
                    "appIconRound" to "@mipmap/ic_launcher_test_round"
                )
            )
            resValue("string", "app_name", "KotlinDslExample$versionNameSuffix")
            buildConfigField("String", "ONESIGNAL_APP_ID", "\"${KeyHelper.KEY_ONESIGNAL_APP_ID_TEST}\"")
            buildConfigField("String", "SERVER_URL", "\"${KeyHelper.KEY_SERVER_URL_TEST}\"")
        }

        create("_prod") {
            dimension = "appType"
            applicationIdSuffix = ""
            versionNameSuffix = ""

            addManifestPlaceholders(
                mapOf(
                    "appIcon" to "@mipmap/ic_launcher_prod",
                    "appIconRound" to "@mipmap/ic_launcher_prod_round"
                )
            )

            resValue("string", "app_name", "KotlinDslExample$versionNameSuffix")
            buildConfigField("String", "ONESIGNAL_APP_ID", "\"${KeyHelper.KEY_ONESIGNAL_APP_ID_PROD}\"")
            buildConfigField("String", "SERVER_URL", "\"${KeyHelper.KEY_SERVER_URL_PROD}\"")
        }
    }

    applicationVariants.all {
        val ext = when (flavorName) {
            "_dev" -> ".devUrlExt"
            "_test" -> ".testUrlExt"
            "_prod" -> ".prodUrlExt"
            else -> null
        }

        ext?.let {
            buildConfigField("String", "BASE_URL", "\"https://api.hostname$ext\"")
            // other +30 buildConfigField
            // For example:
            //buildConfigField("String", "API_KEY", "\"${KeyHelper.KEY_API_KEY}$ext\"")
            //buildConfigField("String", "FEATURE_FLAG", "\"${KeyHelper.KEY_FEATURE_FLAG}$ext\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.register("printKeyPropertiesPath") {
    doLast {
        println("Path to keystore file: ${KeyHelper.getValue(KeyHelper.KEY_STORE_FILE)}")
        println("Path to keystore file: ${KeyHelper.getFilePath()}")
    }
}
