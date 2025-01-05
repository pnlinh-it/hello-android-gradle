import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<ApplicationExtension> {
                // compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
                compileSdk = 34

                defaultConfig {
                    // minSdk = libs.findVersion("minSdk").get().toString().toInt()
                    // targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                    minSdk = 21
                    targetSdk = 34
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx-core-ktx").get())
                "implementation"(libs.findLibrary("androidx-appcompat").get())
                "implementation"(libs.findLibrary("material").get())
                "implementation"(libs.findLibrary("androidx-constraintlayout").get())
                "implementation"(libs.findLibrary("androidx-lifecycle-livedata-ktx").get())
                "implementation"(libs.findLibrary("androidx-lifecycle-viewmodel-ktx").get())
                "implementation"(libs.findLibrary("androidx-navigation-fragment-ktx").get())
                "implementation"(libs.findLibrary("androidx-navigation-ui-ktx").get())
                "implementation"(libs.findLibrary("coroutine-android").get())
            }
        }
    }
}