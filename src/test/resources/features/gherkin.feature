@Debug
Feature: Gherkin Website Testing

  Scenario: Navigate to Gherkin Website, search for a term and verify results
    Given I navigate to Gherkin website
    When I click on search
    And I search for "ref"
    Then I verify search results