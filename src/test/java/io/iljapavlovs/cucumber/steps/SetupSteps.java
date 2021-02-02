package io.iljapavlovs.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.spring.ScenarioScope;
import io.iljapavlovs.cucumber.state.ScenarioState;
import io.iljapavlovs.cucumber.state.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class SetupSteps {

  @Autowired
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
