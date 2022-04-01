# Features

* [Cucumber expressions](https://cucumber.io/docs/cucumber/cucumber-expressions)
  * [example](https://github.com/cucumber/cucumber-jvm/tree/main/examples/java-calculator)
  * [configuration](https://cucumber.io/docs/cucumber/configuration)  
* Parallel execution
* rerun failed tests

# Execution
```
./gradlew clean test allureReport -Dtest-env=local -Dtest-config-file=service.properties
```

### offtopic - Junit 5 support (Not yet ready)

* [Support for Junit 5 Issue Github](https://github.com/cucumber/cucumber-jvm/issues/1149#issuecomment-611716745)

* Docs:
    * [Cucumber Junit5, however still not in official phase](https://github.com/cucumber/cucumber-jvm/tree/main/junit-platform-engine#cucumber-junit-platform-engine)
* Examples:
    * [Spring Boot + Junit 5 + Parallel](https://github.com/mpkorstanje/cucumber-spring-boot-parallel)
    * [Junit 5](https://github.com/cucumber/cucumber-jvm/tree/main/examples/java-calculator-junit5)
    * [Spring](https://github.com/cucumber/cucumber-jvm/tree/main/examples/spring-txn)
    * [Working example with Junit5- CRONN](https://github.com/cronn/cucumber-junit5-example)


* issues:
    * [Afterall and beforeall hooks](https://github.com/cucumber/cucumber-jvm/pull/1876)
  