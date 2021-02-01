package io.iljapavlovs.cucumber.steps;

import io.cucumber.java.en.When;
import io.iljapavlovs.cucumber.state.ScenarioState;

public class ExecutionSteps {

    private final ScenarioState state;

    public ExecutionSteps(ScenarioState state) {
        this.state = state;
    }

    @When("something is executed")
    public void execute() {
        final var userInput = state.getUserInput();
        final var result = Integer.parseInt(userInput);
        state.setResult(result);
    }

}
