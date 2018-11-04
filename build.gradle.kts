plugins {
    application
    checkstyle
    jacoco
    pmd
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("com.github.spotbugs") version "1.6.5"
}

repositories {
    jcenter()
}

checkstyle {
    toolVersion = "8.14"
}

spotbugs {
    toolVersion = "3.1.8"
}

pmd {
    toolVersion = "6.9.0"
}

jacoco {
    toolVersion = "0.8.2"
    applyTo(tasks.run.get())
}

dependencies {
    // Utils libraries
    //implementation("com.google.guava:guava:27.0-jre")
    //implementation("org.apache.commons:commons-lang3:3.8.1")
    //implementation("com.fasterxml.jackson.core:jackson-databind:2.9.7")
    //implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.7")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.7")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.7")

    // Jdbc
    //implementation("com.h2database:h2:1.4.197")
    //implementation("com.zaxxer:HikariCP:3.2.0")

    // Logging libraries
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.11.1")

    // Testing libraries
    testImplementation("org.mockito:mockito-core:2.23.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.3.1")
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

task<JacocoReport>("applicationCodeCoverageReport") {
    executionData(tasks.run.get())
    sourceSets(sourceSets.main.get())
}

tasks {
    shadowJar {
        baseName = project.name
        classifier = null
        version = null
    }

    jar {
        enabled = false
    }

    assemble {
        dependsOn("shadowJar")
    }

    test {
	    useJUnitPlatform()

        testLogging {
		    events("passed", "skipped", "failed")
            showStandardStreams = true
            showStackTraces = true
	    }        
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }

    wrapper {
        gradleVersion = "5.0-rc-1"
    }
}
