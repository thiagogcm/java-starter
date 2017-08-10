import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-RC2")
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.1")
    }
}

plugins {
    application
    checkstyle
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
    toolVersion = "8.1"
}

jacoco {
    toolVersion = "0.7.9"
}

dependencies {
    // Utils libraries
    compile("com.google.guava:guava:23.0")
    compile("org.apache.commons:commons-lang3:3.6")
    compile("com.fasterxml.jackson.core:jackson-core:2.9.0")
    compile("com.fasterxml.jackson.core:jackson-databind:2.9.0")
    compile("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.0")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.0")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.0")

    // Jdbc
    implementation("com.h2database:h2:1.4.196") // Driver
    compile("com.zaxxer:HikariCP:2.6.3")         // Connection pool

    // Logging libraries
    compile("org.apache.logging.log4j:log4j-core:2.8.2")
    compile("org.apache.logging.log4j:log4j-api:2.8.2")
    compile("org.apache.logging.log4j:log4j-slf4j-impl:2.8.2")

    // Testing libraries
    testCompile("org.mockito:mockito-core:2.8.47")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.0-RC2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.0.0-RC2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.0.0-RC2")
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
        gradleVersion = "4.1"
    }
}
