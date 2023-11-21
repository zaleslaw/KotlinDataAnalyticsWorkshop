plugins {
    kotlin("jvm") version "1.9.20"
    id("org.jetbrains.kotlinx.dataframe") version "0.12.0"
    application
}

group = "kds.workshop.berlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:dataframe:0.12.0")
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.5.0-rc-3")
    implementation ("org.jetbrains.kotlinx:kandy-api:0.5.0-rc-2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}