import de.jjohannes.gradle.moduledependencies.gradlebuild.tasks.UniqueModulesPropertiesUpdate

plugins {
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 4
    inputs.dir(layout.projectDirectory.dir("samples"))
}

val updateUniqueModulesProperties = tasks.register<UniqueModulesPropertiesUpdate>("updateUniqueModulesProperties") {
    uniqueModulesProperties.set(layout.projectDirectory.file(
        "src/main/resources/de/jjohannes/gradle/moduledependencies/unique_modules.properties"))
}

tasks.processResources {
    dependsOn(updateUniqueModulesProperties)
}