Feature: A simple feature structure

  This feature outlines basic structure of a cucumber test

  @wikipedia_search
  Scenario: This scenario demonstrates searching something
    Given I am a valid user
    And I require information about "Test"
    When I search for the given subject
    Then I will be shown some real interesting stuff