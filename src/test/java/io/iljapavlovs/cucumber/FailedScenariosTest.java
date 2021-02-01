package io.iljapavlovs.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
        "summary",
        "pretty"
    },
    features = {"@build/rerun.txt"},
    glue = {"io.iljapavlovs.cucumber.stepdefs"}
)
public class FailedScenariosTest {

}

