group = "ru.nsu.e.shelbogashev.canonicalconverter.client"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(project(":converter-core"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
}

tasks.test {
    useJUnitPlatform()
}