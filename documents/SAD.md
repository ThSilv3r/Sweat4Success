# Software Architecture Document

# Table of Contents
- [Introduction](#1-introduction)
    - [Purpose](#11-purpose)
    - [Scope](#12-scope)
    - [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
    - [References](#14-references)
    - [Overview](#15-overview)
- [Architectural Representation](#2-architectural-representation)
- [Architectural Goals and Constraints](#3-architectural-goals-and-constraints)
- [Use-Case View](#4-use-case-view)
- [Logical View](#5-logical-view)
    - [Overview](#51-overview)
    - [Architecturally Significant Design Packages](#52-architecturally-significant-design-packages)
- [Process View](#6-process-view)
- [Deployment View](#7-deployment-view)
- [Implementation View](#8-implementation-view)
- [Data View](#9-data-view)
- [Size and Performance](#10-size-and-performance)
- [Quality](#11-quality)

## 1. Introduction

### 1.1 Purpose
This document provides a comprehensive architectural overview of our system, using a number of different architectural views to depict different aspects of the system. It is intended to capture and convey the significant architectural decisions which have been made on the system.

### 1.2 Scope
This document describes the technical architecture of the SweatForSuccess project and includes class structure, modules and dependencies.

### 1.3 Definitions, Acronyms and Abbreviations

| Abbrevation | Description                            |
| ----------- | -------------------------------------- |
| MVC         | Model View Controller                  |
| SRS         | Software Requirements Specification    |
| UC          | Use Case                               |
| VCS         | Version Control System                 |
| n/a         | not applicable                         |

### 1.4 References
<table>
<thead>
<tr>
<th>Title</th>
<th>Date</th>
<th>Publishing Organization</th>
</tr>
</thead>
<tbody>
<tr>
  <td><a href="https://sweatforsuccess689030981.wordpress.com">Blog</a></td>
<td>18.05.2021</td>
<td>SweatForSuccess</td>
</tr>
  <tr>
  <td><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/documents/SRS.md">SRS</a></td>
<td>29.06.2021</td>
<td>SweatForSuccess </td>
</tr>
</tbody>
</table>

### 1.5 Overview
This project is based on the MVC pattern.

## 2. Architectural Representation
We are trying to implement according to the MVC pattern

## 3. Architectural Goals and Constraints
The application uses MVC as architectural pattern to separate the backend logic form the user interface. Purpose of the controller is to enable communication between components. This includes updating the model and change view based on user interaction.

## 4. Use-Case View
Here you can find our Use Case Diagram:

<img srchttps://github.com/ThSilv3r/Sweat4Success/blob/master/documents/pictures/Use%20Case%20Diagram%20aktuell.jpeg"/>

### Following is a list of our Use Case realizations
<ol>
<li><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCCreateAcount.md">UC Create Account</a></li>
<li><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCCreateWorkout.md">UC Create Workout</a></li>
<li><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCProfil.md">UC Profil</a></li>
<li><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCCommentAcount.md">UC Comment Workout</a></li>
<li><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCSearchAcount.md">UC Search Workout</a></li>
<li><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCShowWorkoutList.md>UC Show List of Workout</a></li>
<li><a href="/https://github.com/ThSilv3r/Sweat4Success/blob/master/UC/UCShowAcount.md">UC Show Workout</a></li>
</ol>
  
## 5. Logical View

### 5.1 Overview
TBD

### 5.2 Architecturally Significant Design Packages
<img src="https://github.com/ThSilv3r/Sweat4Success/blob/master/documents/Screenshot%20(289).png" alt="Alt-Text" title="" />

## 6. Process View
n/a

## 7. Deployment View
n/a

## 8. Implementation View
n/a

### 8.1 Overview
n/a

### 8.2 Layers
n/a

## 9. Data View
n/a

## 10. Size and Performance
The app should be fairly small, very fast and easy to access. Any interaction with the app should be prompt so that the user does not have to wait for the app to start a workout.
## 11. Quality/Metrics
Since we are a unity project our metrics are a little bit more complicated and special.
We are explaining everything on our blog: https://sweatforsuccess689030981.wordpress.com/2021/05/30/s2-w7-metrics/
