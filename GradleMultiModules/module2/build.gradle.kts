plugins {
    id("java")
}

group = "org.example.module2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":module1"))

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}