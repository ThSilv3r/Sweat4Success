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
    Scenario: Create a workout
    Given: The user is logged in
    And: The user has opened “Create workout”
    And: The user enters the needed data in the right places
    And: The user clicks on the save button
    Then: “Create workout” should close

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
