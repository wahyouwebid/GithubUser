import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}
@Suppress("UnstableApiUsage")
android {
    compileSdk = 34

    val baseUrl = System.getenv("BASE_URL") ?: getProperty("BASE_URL", "")

    defaultConfig {
        namespace = "com.wahyouwebid.githubapp"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testOptions.animationsDisabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"$baseUrl\"")
        }

        named("release") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"$baseUrl\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/AL2.0")
            excludes.add("/META-INF/LGPL2.1")
        }
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.google.material)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.sandwich)
    implementation(libs.room.runtime)
    implementation(libs.coil)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

/**
 * Retrieves the value of an environment variable CI/CD with the specified [envName].
 *
 * @param envName The name of the environment variable.
 * @return The value of the environment variable or `null` if the variable is not set.
 */

fun getEnv(envName: String) = System.getenv(envName)

/**
 * Reads a property from the local.properties file using the specified [propertyName].
 *
 * @param propertyName The name of the property to read.
 * @param defaultValue The default value to return if the property is not found or cannot be cast to the expected type.
 * @return The value of the property or the [defaultValue] if the property is not found or cannot be cast to the expected type.
 */

fun <T : Any> getProperty(propertyName: String, defaultValue: T? = null): T? {
    val localPropertiesFile = File("local.properties")

    if (localPropertiesFile.exists()) {
        val properties = Properties()
        try {
            properties.load(rootProject.file(localPropertiesFile).reader())
            val propertyValue = properties.getProperty(propertyName)

            @Suppress("UNCHECKED_CAST")
            return if (propertyValue != null) {
                try {
                    when (defaultValue) {
                        is String -> propertyValue.toString() as T
                        is Int -> propertyValue.toInt() as T
                        is Boolean -> propertyValue.toBoolean() as T
                        else -> defaultValue
                    }
                } catch (e: NumberFormatException) {
                    defaultValue
                }
            } else {
                defaultValue
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return defaultValue
}