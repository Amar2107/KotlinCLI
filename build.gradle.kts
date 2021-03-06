plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"

    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("mysql:mysql-connector-java:8.0.15")
    implementation("com.github.ajalt.clikt:clikt:3.0.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "demo2.AppKt"
}
