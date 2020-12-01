feature: Login

  The User can login

  Scenario: Login successful
  Given The user enters username and password
  And The username is known
  And The password is correct
  Then The User is logged in
  And The User <Home>-page is displayed

  Scenario: Password incorrect
  Given The user enters username and password
  And The username is known
  And The password is incorrect
  Then Error Message is shown

  Scenario: User does not exist
  Given The user enters username and password
  And The username is unknown
  Then Error Message is shown