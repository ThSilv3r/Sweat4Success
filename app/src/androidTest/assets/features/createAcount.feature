Feature: Profil
  Scenario: Create Acount
    Given The user started the app and doesn't have an acount
    When The user enters a username <username> and the input is correct
    And The user enters a e-mail <e-mail> and the input is correct
    And The user enters a password <password> and the input is correct
    And The user enters a birthday <birthday> and the input is correct
    And The user clicks on create Acount <createButton>
    Then The the acount should be created  Feature: Profil

  Scenario: incorrect E-Mail
    Given The user started the app and doesn't have an acount
    When The user enters a username <username> and the input is correct
    And The user enters a e-mail <e-mail> and the input is incorrect
    And The user enters a password <password> and the input is correct
    And The user enters a birthday <birthday> and the input is correct
    And The user clicks on create Acount <createButton>
    Then An error message should be shown

  Scenario: incorrect username
    Given The user started the app and doesn't have an acount
    When The user enters a username <username> and the input is incorrect
    And The user enters a e-mail <e-mail> and the input is correct
    And The user enters a password <password> and the input is correct
    And The user enters a birthday <birthday> and the input is correct
    And The user clicks on create Acount <createButton>
    Then An error message should be shown