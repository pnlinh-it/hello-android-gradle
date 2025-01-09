import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class FlavorPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseAppModuleExtension> {
                flavorDimensions += listOf("environment", "price")

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
            }
        }
    }
}