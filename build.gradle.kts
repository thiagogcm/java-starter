plugins {
    application
    checkstyle
    jacoco
    pmd
    id("com.github.johnrengelman.shadow") version "4.0.3"
    id("com.github.spotbugs") version "1.6.9"
}

repositories {
    jcenter()
}

application {
    mainClassName = "io.github.thiagogcm.javastarter.App"
}

checkstyle {
    toolVersion = "8.16"
}

jacoco {
    toolVersion = "0.8.2"
    applyTo(tasks.run.get())
}

java {
    group = "io.github.thiagogcm.javastarter"
    version = "1.0"

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pmd {
    toolVersion = "6.10.0"
}

spotbugs {
    toolVersion = "3.1.10"
}

dependencies {
    // Utils libraries
    //implementation("com.google.guava:guava:27.0.1-jre")
    //implementation("org.apache.commons:commons-lang3:3.8.1")
    //implementation("com.fasterxml.jackson.core:jackson-databind:2.9.8")
    //implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.8")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.8")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.8")

    // Jdbc
    //implementation("com.h2database:h2:1.4.197")
    //implementation("com.zaxxer:HikariCP:3.3.0")

    // Logging libraries
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.11.1")

    // Testing libraries
    testImplementation("org.mockito:mockito-core:2.23.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.3.2")
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
        baseName = project.name
        classifier = null
        version = null
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
        gradleVersion = "5.1.1"
    }
}
