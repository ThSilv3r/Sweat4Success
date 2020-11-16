# Use-Case Specification: Create Acount
## 1.Create acount
### 1.1 Brief Description
This use case describes the creation of an account (CRUD).
### 1.2 Mockup
![](https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/pictures/Create%20Account.png)
### 1.3 Screenshot
(tbd)
## 2. Flow of Events
### 2.1 Basic Flow
![](https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/pictures/CreateAccount.PNG)
Activity Diagram
### 2.2 Narative
      Feature: Profil
      Scenario: Create Acount
      Given: The user started the app and doesn't have an acount
      When: The user enters a username <username> and the input is correct
      And: The user enters a e-mail <e-mail> and the input is correct
      And: The user enters a password <password> and the input is correct
      And: The user enters a birthday <birthday> and the input is correct
      And: The user clicks on "create Acount" <createButton>
      Then: The the acount should be created

### 2.3 Alternative Flows
(n/a)
## 3 Special Requirements
(n/a)
## 4 Preconditions
The main preconditions for this use case are:

The user started the app and doesn't have an acount

## 5 Postconditions
The acount must be saved in the database.
## 6 Extension Points
(tbd)
