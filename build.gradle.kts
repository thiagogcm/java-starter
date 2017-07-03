import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4")
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.1")
    }
}

plugins {
    application
    checkstyle
    idea
    jacoco
}

apply {
    plugin("com.github.johnrengelman.shadow")
    plugin("org.junit.platform.gradle.plugin")
}

repositories {
    jcenter()
}

checkstyle {
    toolVersion = "8.0"
}

jacoco {
    toolVersion = "0.7.9"
}

dependencies {
    // Utils libraries
    //compile("com.google.guava:guava:22.0")
    //compile("org.apache.commons:commons-lang3:3.5")
    //compile("com.fasterxml.jackson.core:jackson-core:2.8.8")
    //compile("com.fasterxml.jackson.core:jackson-databind:2.8.8")
    //compile("com.fasterxml.jackson.module:jackson-module-parameter-names:2.8.8")
    //compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.8.8")
    //compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.8.8")

    // Jdbc
    //implementation("com.h2database:h2:1.4.195") // Driver
    //compile("com.zaxxer:HikariCP:2.6.2") // Connection pool

    // Logging libraries
    compile("org.apache.logging.log4j:log4j-core:2.8.2")
    compile("org.apache.logging.log4j:log4j-api:2.8.2")
    compile("org.apache.logging.log4j:log4j-slf4j-impl:2.8.2")

    // Testing libraries
    testCompile("org.mockito:mockito-core:2.8.9")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.0-M4")
    //testImplementation("org.junit.jupiter:junit-jupiter-params:5.0.0-M4")
    //testImplementation("org.junit.platform:junit-platform-runner:1.0.0-M4")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.0.0-M4")
    // To run JUnit4 tests:
    //testCompile("junit:junit:4.12")
    //testRuntimeOnly("org.junit.vintage:junit-vintage-engine:4.12.0-M4")
}

application {
    mainClassName = "io.github.thiagogcm.javastarter.App"
}

java {
    group = "io.github.thiagogcm.javastarter"
    version = "1.0"

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    "shadowJar"(ShadowJar::class) {
        baseName = project.name
        classifier = null
        version = null
    }

    "jar" {
        enabled = false
    }

    "assemble" {
        dependsOn("shadowJar")
    }

    "wrapper"(Wrapper::class) {
        gradleVersion = "4.0"
    }
}
