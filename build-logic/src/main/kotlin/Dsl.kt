import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.util.Optional

internal fun DependencyHandler.implementation(dependency: Optional<Provider<MinimalExternalModuleDependency>>) =
    add("implementation", dependency.get())

internal fun ApplicationExtension.kotlinOptions(configure: Action<KotlinJvmOptions>): Unit =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)

internal fun Project.kotlin(configure: Action<KotlinAndroidProjectExtension>): Unit =
   extensions.configure("kotlin", configure)
