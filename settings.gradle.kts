plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "canonical-converter"
include("converter-core")
include("converter-client")
