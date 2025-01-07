import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.util.Optional

class MyApplicationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            val compileSdkVersion = libs.findVersion("compileSdk").get().requiredVersion.toInt()
            val minSdkVersion = libs.findVersion("minSdk").get().requiredVersion.toInt()
            val appVersion = libs.findVersion("appVersion").get().requiredVersion

            extensions.configure<BaseAppModuleExtension> {
                compileSdk = compileSdkVersion

                defaultConfig {
                    applicationId = "com.example.myapplication"
                    minSdk = minSdkVersion
                    targetSdk = compileSdkVersion
                    versionCode = 1
                    versionName = appVersion

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                flavorDimensions.add("environment")
                flavorDimensions.add("price")

                productFlavors {
                    create("demo") {
                        dimension = "environment"
                    }

                    create("real") {
                        dimension = "environment"
                    }

                    create("paid") {
                        dimension = "price"
                    }
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
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }

                kotlinOptions {
                    jvmTarget = "11"
                }

                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                implementation(libs.findLibrary("androidx-core-ktx"))
            }
        }
    }
}

internal fun DependencyHandler.implementation(dependency: Optional<Provider<MinimalExternalModuleDependency>>) =
    add("implementation", dependency.get())

internal fun Project.kotlinOptions(configure: Action<KotlinJvmOptions>): Unit =
    extensions.configure("kotlinOptions", configure)