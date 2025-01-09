plugins {
    `kotlin-dsl`
}

group = "com.example.convention.plugin"

gradlePlugin {
    plugins {
        create("AndroidApplicationPlugin") {
            id = "com.example.myapplication"
            implementationClass = "AndroidApplicationPlugin"
        }
        create("FlavorPlugin") {
            id = "com.example.flavor"
            implementationClass = "FlavorPlugin"
        }
    }
}

dependencies {
    // If we want to use android-app.gradle.kts, we need to use implementation
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}
