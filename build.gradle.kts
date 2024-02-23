plugins {
    kotlin("jvm") version "1.9.22"
}

group = "ru.nsu.e.shelbogashev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("ch.qos.logback:logback-classic:1.4.14")
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
        implementation("ch.qos.logback:logback-classic:1.4.14")
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}