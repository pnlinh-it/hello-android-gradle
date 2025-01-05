plugins {
   `kotlin-dsl`
}

group = "com.example.convention.plugin"

dependencies {
   compileOnly(libs.android.gradlePlugin)
   compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
   plugins {
      register("myCustomPlugin") {
         id = "com.example.mycustomplugin"
         implementationClass = "MyCustomPlugin"
      }

      register("AndroidApplicationPlugin") {
         id = "com.example.application"
         implementationClass = "AndroidApplicationPlugin"
      }
   }
}

