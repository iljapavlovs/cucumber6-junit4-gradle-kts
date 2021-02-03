package io.iljapavlovs.cucumber.steps;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.Given;
import io.iljapavlovs.cucumber.state.ScenarioState;
import io.iljapavlovs.cucumber.state.SomeObject;
import javax.inject.Inject;

@ScenarioScoped
public class SetupSteps {

  @Inject
  private ScenarioState state;

//  public SetupSteps(ScenarioState state) {
//    this.state = state;
//  }

  @Given("something exists")
  public void something() {
    state.setUserInput("123");
  }

  @Given("something else exists")
  public void somethingElse() {
    state.setUserInput("abc");
  }

  @Given("some object exists")
  public void someObjectExists(SomeObject someObject) {
    state.setSomeObject(someObject);
  }
}
