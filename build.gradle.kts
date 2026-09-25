plugins {
    kotlin("jvm") version "2.0.20"
    `maven-publish`
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
}

// Publishing — allows consumers (e.g. the test suite) to use this as a jar via mavenLocal
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}


