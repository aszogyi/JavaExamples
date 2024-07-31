plugins {
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.springframework.boot") version "2.1.8.RELEASE" apply false
    kotlin("jvm") version "1.3.50" apply false
    kotlin("plugin.spring") version "1.3.50" apply false
    java
}


defaultTasks("assemble")

tasks.forEach {
    if (it.name == "bootRun") {
        it.enabled = false
    }
}

/*
group = "org.example"
version = "1.0-SNAPSHOT"

 */

allprojects {
    group = "org.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

repositories {
    mavenCentral()
}
/*
dependencies {
    constraints {
        implementation("org.apache.commons:commons-text:1.11.0")
    }

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

 */

subprojects {
    apply(plugin = "java")

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}