Java Starter
=============
This a simple template to start a Java project using: 
* Gradle 4 with kotlin script;
* ShadowJar plugin to package a executable (fat)jar
* Checkstyle plugin with Google style guide rules;
* Jacoco plugin
* JUnit 5 + Mockito;
* Logging with SLF4J + Log4j2

Prerequisites
------------
* JDK 8+

How to use it
-------------
* Clone this repo and delete the `.git` dir
```
 git clone https://github.com/thiagogcm/java-starter.git PROJECT_NAME
```
* Replace `PROJECT_NAME` with a new name
* Rename the `rootProject.name` property in the `settings.gradle` file with the new project name

##### Now you're good to go:
```
./gradlew build
java -jar build/libs/PROJECT_NAME.jar
```