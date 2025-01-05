plugins {
   kotlin("jvm") version "1.5.21" // or the appropriate Kotlin version
   `kotlin-dsl`
   `java-gradle-plugin`
}

gradlePlugin {
   plugins {
      create("myCustomPlugin") {
         id = "com.example.mycustomplugin"
         implementationClass = "MyCustomPlugin"
      }
   }
}

repositories {
   mavenCentral() // Repository for resolving dependencies
}

dependencies {
   implementation(gradleApi())
   implementation(localGroovy())
}
