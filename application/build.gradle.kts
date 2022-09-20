val ktorVersion: String by project
val logbackVersion: String by project
val kodeinVersion: String by project
val kotlinVersion: String by rootProject

plugins {
    application
}

application {
    mainClass.set("com.igorralexsander.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("org.kodein.di:kodein-di-generic-jvm:$kodeinVersion")

    implementation(project(":domain"))
    implementation(project(":infraestructure"))

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
}
