plugins {
    application
    checkstyle
    jacoco
    pmd
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("com.github.spotbugs") version "2.0.1"
}

repositories {
    jcenter()
}

application {
    mainClassName = "io.github.thiagogcm.javastarter.App"
}

checkstyle {
    toolVersion = "8.26"
}

jacoco {
    toolVersion = "0.8.5"
    applyTo(tasks.run.get())
}

java {
    group = "io.github.thiagogcm.javastarter"
    version = "1.0"

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pmd {
    toolVersion = "6.19.0"
}

spotbugs {
    toolVersion = "3.1.12"
}

dependencies {
    // Utils libraries
    //implementation("com.google.guava:guava:28.1-jre")
    //implementation("org.apache.commons:commons-lang3:3.9")
    //implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
    //implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.10.1")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.10.1")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.1")

    // Jdbc
    //implementation("com.h2database:h2:1.4.200")
    //implementation("com.zaxxer:HikariCP:3.4.1")

    // Logging libraries
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.12.1")

    // Testing libraries
    testImplementation("org.mockito:mockito-core:3.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.5.2")
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
        gradleVersion = "6.0.1"
    }
}
