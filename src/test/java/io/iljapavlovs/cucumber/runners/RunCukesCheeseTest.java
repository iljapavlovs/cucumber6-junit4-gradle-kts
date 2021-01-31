package io.iljapavlovs.cucumber.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
        "summary",
        "pretty",
        "rerun:build/rerun.txt",
    },
    features = {"src/test/resources/features"},
    tags = "@cheese",
    glue = {"io.iljapavlovs.cucumber.stepdefs"}
)
public class RunCukesCheeseTest {

}

