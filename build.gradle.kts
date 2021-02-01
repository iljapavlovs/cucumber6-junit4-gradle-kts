import io.qameta.allure.gradle.AllureExtension

plugins {
    java
    idea
    id("io.qameta.allure") version "2.8.1"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.cucumber:cucumber-java:6.9.1")
    testImplementation("io.cucumber:cucumber-junit:6.9.1")
//    testImplementation("io.cucumber:cucumber-spring:6.9.1")
    testImplementation("io.cucumber:cucumber-picocontainer:6.9.1")


    testImplementation("org.projectlombok:lombok:1.18.16")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("com.codeborne:selenide:5.18.0")
    testImplementation("org.testcontainers:selenium:1.15.1")

    testImplementation("io.qameta.allure:allure-cucumber6-jvm:2.13.8")
    testImplementation("org.awaitility:awaitility:4.0.3")


    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")





}

tasks.getByName<Test>("test") {
    ignoreFailures = true
    include("**/RunCukesAllTest.class")
    include("**/FailedScenariosTest.class")

    testLogging.showStandardStreams = true
    systemProperties(System.getProperties().toMap() as Map<String,Object>)

    // will not work - works only with Java Classes
    maxParallelForks = 3
}



configure<AllureExtension> {
    autoconfigure = false
    aspectjweaver = true
    version = "2.13.8"
    downloadLink = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.13.8/allure-commandline-2.13.8.zip"

    clean = true
}

