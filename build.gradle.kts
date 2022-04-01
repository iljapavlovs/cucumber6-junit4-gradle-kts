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
    jcenter()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.16")
    testImplementation("io.cucumber:cucumber-java:6.9.1")
    testImplementation("io.cucumber:cucumber-junit:6.9.1")
    implementation("io.cucumber:cucumber-guice:6.9.1")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("com.google.guava:guava:23.0")



    compileOnly("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.projectlombok:lombok:1.18.16")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("com.codeborne:selenide:5.18.0")
    testImplementation("org.testcontainers:selenium:1.16.3")
    testImplementation("org.testcontainers:testcontainers:1.16.3")

    testImplementation("io.qameta.allure:allure-cucumber6-jvm:2.13.8")
    testImplementation("org.awaitility:awaitility:4.0.3")


    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.27.2")
    implementation("commons-configuration:commons-configuration:1.10")



    implementation("com.google.inject:guice:4.1.0")
    implementation("com.mycila.guice.extensions:mycila-guice-jsr250:4.0.rc1")
    implementation("com.mycila.guice.extensions:mycila-guice-closeable:4.0.rc1")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation("javax.annotation:javax.annotation-api:1.3.2")

}

tasks.getByName<Test>("test") {
    ignoreFailures = true
    include("**/RunCukesAllTest.class")
    include("**/FailedScenariosTest.class")
//    will run in Testcontainers
    include("**/RunTestcontainersTest.class")


    testLogging.showStandardStreams = true
    systemProperties(System.getProperties().toMap() as Map<String,Object>)

    // will not work - works only with Java Classes
    maxParallelForks = 3
}



configure<AllureExtension> {
    autoconfigure = false
    aspectjweaver = true
    version = "2.17.3"
    downloadLink = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.17.3/allure-commandline-2.17.3.zip"

    clean = true


}

