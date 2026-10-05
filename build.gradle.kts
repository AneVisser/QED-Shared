plugins {
    kotlin("jvm") version "2.4.20"
}

group = "com.qed"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(kotlin("stdlib"))
    // api (not implementation): consumers of QED-Shared also need qed.contract types,
    // because the RequestType alias below points to them
    api("com.qed:QED-Api-Contract:1.0.0")
}


