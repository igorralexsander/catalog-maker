import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val kotlin_version: String by project


repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.7.10"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies{
        testImplementation(kotlin("test-junit5"))
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

}
