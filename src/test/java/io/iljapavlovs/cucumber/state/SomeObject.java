package io.iljapavlovs.cucumber.state;

import io.cucumber.guice.ScenarioScoped;


public class SomeObject {

  private String someString;
  private int someInt;

  public String getSomeString() {
    return someString;
  }

  public void setSomeString(String someString) {
    this.someString = someString;
  }

  public int getSomeInt() {
    return someInt;
  }

  public void setSomeInt(int someInt) {
    this.someInt = someInt;
  }
}
