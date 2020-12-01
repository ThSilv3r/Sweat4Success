Feature: Create workout

  The User can create a workout.

  Scenario: Create workout
    Given The user started the app
    And The user is a registerd user
    When The user opens <createWorkout>
    And The user added an description <description> and the input is correct
    And The user choose exercises <exercises>
    And The user choose categories <category>
    And The user clicks on save <saveButton>
    Then The <createWorkout>-page is closed
    And The workout is saved