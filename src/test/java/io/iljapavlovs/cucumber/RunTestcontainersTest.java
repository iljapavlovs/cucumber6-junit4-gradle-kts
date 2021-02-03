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
    features = {"src/test/resources/features"},
    tags = "@docker",
    glue = {"io.iljapavlovs.cucumber.steps"}
)
public class RunTestcontainersTest extends AbstractCiAcceptanceTest {



}
