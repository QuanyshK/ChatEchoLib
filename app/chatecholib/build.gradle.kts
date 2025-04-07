plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id ("maven-publish")
}


android {
    namespace = "com.example.chatecholib"
    compileSdk = 35

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.quanyshk"
            artifactId = "chatecholib"
            version = "1.0.0"
            afterEvaluate { from(components["release"]) }
        }
    }
}
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/quanyshk/chatecholib")
        credentials {
            username = project.findProperty("quanyshk") as String? ?: ""
            password = project.findProperty("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") as String? ?: ""
        }
    }
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.logging)

}