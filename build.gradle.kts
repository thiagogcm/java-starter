import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4")
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.0")
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
    toolVersion = "7.8"
}

dependencies {
    // Utils libraries
    compile("com.google.guava:guava:22.0")

    // Logging libraries
    compile("org.apache.logging.log4j:log4j-api:2.8.2")
    compile("org.apache.logging.log4j:log4j-core:2.8.2")
    compile("org.apache.logging.log4j:log4j-slf4j-impl:2.8.2")

    // Testing libraries
    testCompile("org.mockito:mockito-core:2.8.9")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.0-M4")
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
        gradleVersion = "4.0-rc-1"
    }
}
