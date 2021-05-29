# Use-Case Specification: Friend System
## 1.Friend System
### 1.1 Brief Description
This use case describes the Friend System (CRUD).
### 1.2 Mockup
![](https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/pictures/UCFriendSystem.PNG)
### 1.3 Screenshot
(tbd)
## 2. Flow of Events
### 2.1 Basic Flow
![](![](https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/pictures/UCFriendSystem.PNG))
Activity Diagram
### 2.2 Narative
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

### 2.3 Alternative Flows
(n/a)
## 3 Special Requirements
(n/a)
## 4 Preconditions
The main preconditions for this use case are:

The user started the app and has an acount.
The user navigated  to an user profil.

## 5 Postconditions
The acount must be saved in the database.
## 6 Extension Points
(tbd)
