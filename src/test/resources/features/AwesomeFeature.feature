@all
Feature: Awesome Feature

  Scenario: You can re-use existing steps
    Given something exists
    When something is executed
    Then some result will be calculated

  Scenario: Example with parameter types
    Given some object exists
      | someInt | someString |
      | 1       | Random     |
    Then some object is equal to
      | someInt | someString |
      | 1       | Random     |

