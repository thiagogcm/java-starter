import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0")
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
    toolVersion = "8.2"
}

jacoco {
    val run : JavaExec by tasks

    toolVersion = "0.7.9"
    applyTo(run)
}

dependencies {
    // Utils libraries
    //implementation("com.google.guava:guava:23.0")
    //implementation("org.apache.commons:commons-lang3:3.6")
    //implementation("com.fasterxml.jackson.core:jackson-core:2.9.1")
    //implementation("com.fasterxml.jackson.core:jackson-databind:2.9.1")
    //implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.1")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.1")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.1")

    // Jdbc
    //implementation("com.h2database:h2:1.4.196")
    //implementation("com.zaxxer:HikariCP:2.7.1")

    // Logging libraries
    implementation("org.apache.logging.log4j:log4j-core:2.9.0")
    implementation("org.apache.logging.log4j:log4j-api:2.9.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.9.0")

    // Testing libraries
    testCompile("org.mockito:mockito-core:2.10.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.0.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.0.0")
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

    "wrapper"(Wrapper::class) {
        gradleVersion = "4.2"
    }
}

 tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}

afterEvaluate {
    val junitPlatformTest : JavaExec by tasks
    
    jacoco {
        applyTo(junitPlatformTest)
    }

    task<JacocoReport>("jacocoJunit5TestReport") {
        executionData(junitPlatformTest)
        sourceSets(java.sourceSets["main"])
        sourceDirectories = files(java.sourceSets["main"].allSource.srcDirs)
        classDirectories = files(java.sourceSets["main"].output)
    }
}
