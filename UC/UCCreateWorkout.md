# Use-Case Specification: Create workout
## 1.Create workout
### 1.1 Brief Description
This use case describes the creation of a Workout.(CRUD)
### 1.2 Mockup
![](https://github.com/ThSilv3r/SweatForSuccess/blob/Jonas/Pictures/CreateWorkout.PNG)
### 1.3 Screenshot
(tbd)
## 2. Flow of Events
### 2.1 Basic Flow
![Activity Diagram] (https://github.com/ThSilv3r/SweatForSuccess/blob/Jonas/Pictures/UCCreateWorkout.png)
### 2.2 Narative
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

### 2.3 Alternative Flows
(n/a)
## 3 Special Requirements
(n/a)
## 4 Preconditions
The main preconditions for this use case are:

    The user has an account.
    The user is logged in.
    The user has started the app and has navigated to "Create workout".

## 5 Postconditions
The workout must be saved in the database.
## 6 Extension Points
(tbd)
