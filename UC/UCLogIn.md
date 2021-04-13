# Use Case Specification: Log In
## 1.Log In 
### 1.1 Brief Description
A registered User needs to be able to log into his account to use all functions including special recommended workouts and tracking their own workouts.
### 1.2 Mockup
![](https://github.com/ThSilv3r/Sweat4Success/blob/Nils/UC/pictures/Untitled%20Diagram.png)
### 1.3 Screenshot
![](https://github.com/ThSilv3r/Sweat4Success/blob/Nils/UC/pictures/Login%20Screen.png)
## 2. Flow of Events

### 2.1 Basic Flow
tbd
### 2.2 Narative
    feature: Login

  The User can login

  Scenario: Login successful
  Given The user enters username and password
  And The username is known
  And The password is correct
  Then The User is logged in
  And The User <Home>-page is displayed

### 2.3 Alternative Flows
Scenario: Password incorrect
  Given The user enters username and password
  And The username is known
  And The password is incorrect
  Then Error Message is shown

  Scenario: User does not exist
  Given The user enters username and password
  And The username is unknown
  Then Error Message is shown
  
## 3 Special Requirements
(n/a)
## 4 Preconditions
The user opened the app and already has an account.

## 5 Postconditions
The user is directed into his account.
The user sees the account home page.
Account data must be loaded from the database.

## 6 Extension Points
n/a
