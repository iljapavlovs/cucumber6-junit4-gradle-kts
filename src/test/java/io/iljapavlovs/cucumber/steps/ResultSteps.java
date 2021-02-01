package io.iljapavlovs.cucumber.steps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.java.en.Then;
import io.iljapavlovs.cucumber.state.ScenarioState;
import io.iljapavlovs.cucumber.state.SomeObject;

public class ResultSteps {

  private final ScenarioState state;

  public ResultSteps(ScenarioState state) {
    this.state = state;
  }

  @Then("some result will be calculated")
  public void result() {
    assertThat(state.getResult()).isGreaterThan(0);
  }

  @Then("no result can be calculated")
  public void noResult() {
    assertThat(state.getResult()).isLessThan(0);
  }

  @Then("some object is equal to")
  public void someObjectIsEqualTo(SomeObject someObject) {
    assertThat(state.getSomeObject()).isEqualToComparingFieldByFieldRecursively(someObject);
  }
}
