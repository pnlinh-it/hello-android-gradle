import org.gradle.api.Plugin
import org.gradle.api.Project

class MyCustomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("printMessage") {
            doLast {
                println("Hello from the Custom Plugin!")
            }
        }
    }
}