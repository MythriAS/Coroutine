plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter() //ADD THIS ONE TO RUN COROUTINE CODE
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //COROUTINE
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("junit:junit:4.12")//TEST coroutine
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}