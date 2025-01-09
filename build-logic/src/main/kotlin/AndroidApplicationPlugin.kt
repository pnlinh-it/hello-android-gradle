import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.example.flavor")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            val compileSdkVersion = libs.findVersion("compileSdk").get().requiredVersion.toInt()
            val minSdkVersion = libs.findVersion("minSdk").get().requiredVersion.toInt()
            val appVersion = libs.findVersion("appVersion").get().requiredVersion

            extensions.configure<ApplicationExtension> {
                compileSdk = compileSdkVersion

                defaultConfig {
                    applicationId = "com.example.myapplication"
                    minSdk = minSdkVersion
                    targetSdk = compileSdkVersion
                    versionCode = 1
                    versionName = appVersion

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

                buildFeatures {
                    viewBinding = true
                }

                // From kotlin 1.8
                tasks.withType<KotlinJvmCompile>().configureEach {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
                // kotlin {
                //     compilerOptions {
                //         jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
                //     }
                // }

                // tasks.withType<KotlinCompile>().configureEach {
                //     kotlinOptions {
                //         jvmTarget = "11"
                //     }
                // }
                //
                // kotlinOptions {
                //     jvmTarget = "11"
                // }
            }

            dependencies {
                implementation(libs.findLibrary("androidx-core-ktx"))
            }
        }
    }
}
