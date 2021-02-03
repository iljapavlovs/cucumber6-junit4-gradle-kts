package io.iljapavlovs.cucumber.steps;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.When;
import io.iljapavlovs.cucumber.state.ScenarioState;
import javax.inject.Inject;

@ScenarioScoped
public class ExecutionSteps {

    @Inject
    private ScenarioState state;

//    public ExecutionSteps(ScenarioState state) {
//        this.state = state;
//    }

    @When("something is executed")
    public void execute() {
        final var userInput = state.getUserInput();
        final var result = Integer.parseInt(userInput);
        state.setResult(result);
    }

}
