val ktorVersion: String by project
val resilience4jVersion: String by project

group = "com.igorralexsander"
version = "0.0.1"

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")

    implementation("io.github.resilience4j:resilience4j-circuitbreaker:$resilience4jVersion")
    implementation("io.github.resilience4j:resilience4j-kotlin:$resilience4jVersion")
    implementation("io.github.resilience4j:resilience4j-retry:$resilience4jVersion")
    //implementation("io.github.resilience4j:resilience4j-micrometer:$resilience4jVersion")

    implementation("org.jsoup:jsoup:1.14.3")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation(project(":domain"))
}
