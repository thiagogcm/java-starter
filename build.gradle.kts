plugins {
    application
    checkstyle
    jacoco
    pmd
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("com.github.spotbugs") version "4.7.0"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.github.thiagogcm.javastarter.App")
}

checkstyle {
    toolVersion = "8.41"
}

jacoco {
    toolVersion = "0.8.6"
    applyTo(tasks.run.get())
}

java {
    group = "io.github.thiagogcm.javastarter"
    version = "1.0"

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pmd {
    toolVersion = "6.32.0"
}

dependencies {
    // Utils libraries
    //implementation("com.google.guava:guava:30.1.1-jre")
    //implementation("org.apache.commons:commons-lang3:3.12")
    //implementation("com.fasterxml.jackson.core:jackson-databind:2.12.2")
    //implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.12.2")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.12.2")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.2")

    // Jdbc
    //implementation("com.h2database:h2:1.4.200")
    //implementation("com.zaxxer:HikariCP:4.0.3")

    // Logging libraries
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")

    // Testing libraries
    testImplementation("org.mockito:mockito-core:3.8.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.7.1")
}

task<JacocoReport>("applicationCodeCoverageReport") {
    executionData(tasks.run.get())
    sourceSets(sourceSets.main.get())
}

tasks {
    assemble {
        dependsOn("shadowJar")
    }

    jacocoTestReport {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }

    jar {
        enabled = false
    }

    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set("")
    }

    test {
        testLogging {
		    events("passed", "skipped", "failed")
            showStandardStreams = true
            showStackTraces = true
	    }        

	    useJUnitPlatform()
    }

    wrapper {
        gradleVersion = "7.0-rc-1"
    }
}
