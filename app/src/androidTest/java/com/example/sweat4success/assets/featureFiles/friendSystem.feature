Feature: Friend System

  Scenario: Add Friend
    Given The user started the app
    And The user is a registerd user
    And The other user is not a friend
    When The user opens <userProfile>
    And The user clicks on <addFriend>
    Then The <addFriend> Button will change text
    And The user is  added to the friend list

  Scenario: Remove Friend
    Given The user started the app
    And The user is a registerd user
    And The other user is a friend
    When The user opens <userProfile>
    And The user clicks on <addFriend>
    Then The <addFriend> Button will change text
    And The user is deleted from the friend list