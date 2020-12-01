Feature: Edit Profil
  The User should be able to edit the profil.

  Scenario: Edit Name
    Given The user started the app
    And The user is a registerd user
    When The user opens the profil
    And The user clicks on the name <name>
    And The user edits the name <name> and the input is correct
    And The user clicks on save <saveButton>
    Then The name should be updated


  Scenario: Edit Category
    Given The user started the app
    And The user is a registerd user
    When The user opens the profil
    And The user clicks on the category <category>
    And The user edits the category <category> and the input is correct
    And The user clicks on save <saveButton>
    Then The category should be updated

  Scenario: Edit Height
    Given The user started the app
    And The user is a registerd user
    When The user opens the profil
    And The user clicks on the height <height>
    And The user edits the height <height> and the input is correct
    And The user clicks on save <saveButton>
    Then The height should be updated
    And The BMI shold be updated

  Scenario: Edit Weight
    Given The user started the app
    And The user is a registerd user
    When The user opens the profil
    And The user clicks on the weight <weight>
    And The user edits the weight <weight> and the input is correct
    And The user clicks on save <saveButton>
    Then The weight should be updated
    And The BMI should be updated