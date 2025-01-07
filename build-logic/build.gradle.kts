plugins {
   `kotlin-dsl`
//   kotlin("jvm") version "1.5.21" // or the appropriate Kotlin version
//   `java-gradle-plugin`
}

group = "com.example.convention.plugin"

gradlePlugin {
   plugins {
      create("MyApplicationPlugin") {
         id = "com.example.myapplication"
         implementationClass = "MyApplicationPlugin"
      }

//      register("AndroidApplicationPlugin") {
//         id = "com.example.application"
//         implementationClass = "AndroidApplicationPlugin"
//      }
   }
}

dependencies {
   implementation(libs.android.gradle.plugin)
   implementation(libs.kotlin.gradle.plugin)
}
