import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.3")
    }
}

plugins {
    application
    checkstyle
    jacoco
}

apply {
    plugin("com.github.johnrengelman.shadow")
}

repositories {
    jcenter()
}

checkstyle {
    toolVersion = "8.9"
}

jacoco {
    val run : JavaExec by tasks

    toolVersion = "0.8.1"
    applyTo(run)
}

dependencies {
    // Utils libraries
    //implementation("com.google.guava:guava:24.1-jre")
    //implementation("org.apache.commons:commons-lang3:3.7")
    //implementation("com.fasterxml.jackson.core:jackson-databind:2.9.5")
    //implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.5")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.5")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.5")

    // Jdbc
    //implementation("com.h2database:h2:1.4.196")
    //implementation("com.zaxxer:HikariCP:2.7.8")

    // Logging libraries
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.11.0")

    // Testing libraries
    testCompile("org.mockito:mockito-core:2.18.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.1.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.1")
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

    "applicationCodeCoverageReport"(JacocoReport::class) {
        val run by tasks

        executionData(run)
        sourceSets(java.sourceSets["main"])
    }

    "jacocoTestReport"(JacocoReport::class) {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }

    "wrapper"(Wrapper::class) {
        gradleVersion = "4.7"
    }
}
