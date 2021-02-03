package io.iljapavlovs.cucumber.state;

import io.cucumber.guice.ScenarioScoped;

/**
 * An instance of this class will be injected in each scenario, thus allowing all scenarios to be run in parallel.
 */
@ScenarioScoped
public class ScenarioState {

    private String userInput;
    private int result;

    private SomeObject someObject;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public SomeObject getSomeObject() {
        return someObject;
    }

    public void setSomeObject(SomeObject someObject) {
        this.someObject = someObject;
    }
}
