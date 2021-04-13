<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <h1> Software Requirement Specification </h1>
  <link rel="stylesheet" href="https://stackedit.io/style.css" />
</head>

<body class="stackedit">
  <div class="stackedit__left">
    <div class="stackedit__toc">    
<ul>
<li><a href="#sweat-for-success">Sweat for Success</a></li>
<li><a href="#software-requirements-specification-for-fitness-app">Software Requirements Specification For Fitness App</a></li>
<li><a href="#revision-history">Revision History</a></li>
<li>
<ul>
<li><a href="#introduction">1. Introduction</a></li>
<li><a href="#overall-description">2. Overall Description</a></li>
<li><a href="#specific-requirements">3. Specific Requirements</a></li>
<li><a href="#supporting-information">4. Supporting Information</a></li>
</ul>
</li>
</ul>

  </div>
  <div class="stackedit__right">
    <div class="stackedit__html">
      <h1 id="sweat-for-success">Sweat for Success</h1>
<h1 id="software-requirements-specification-for-fitness-app">Software Requirements Specification For Fitness App</h1>
<p>Version &lt;1.0&gt;</p>
<h1 id="revision-history">Revision History</h1>

<table>
<thead>
<tr>
<th>Date</th>
<th>Version</th>
<th>Description</th>
<th>Author</th>
<th></th>
</tr>
</thead>
<tbody>
<tr>
<td>&lt;13/Okt/2020&gt;</td>
<td>&lt;1.0&gt;</td>
<td>First Draft - not all aspects included</td>
<td>Nils, Marina, Jonas</td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>
</table><h1 id="toc"></h1>
<h2 id="introduction">1. Introduction</h2>
<h3 id="purpose">1.1 Purpose</h3>
<p>This Software Requirements Specification (SRS) describes all specifications for the application “Sweat for Success”. It includes an overview about this project and its vision, detailed information about the planned features and boundary conditions of the development process.</p>
<h3 id="scope">1.2 Scope</h3>
<p>The project is going to be realized as an Android App. Planned funtions include:<br>
• Account system<br>
Users can create accounts to store workouts and data concerning their fitness level and workout plans.<br>
• For You Page<br>
Users can share their favorite workouts and see workouts from other users to get inspired. Further they can update their progress and past results.<br>
• Storing Data<br>
Workouts and User data is stored for tracking and comparison purposes.<br>
• Find your Workout<br>
Users can use different search and filter options to find the workout they like</p>
<h3 id="definitions-acronyms-and-abbreviations">1.3 Definitions, Acronyms and Abbreviations</h3>
<p>TBD – To be determined<br>
UC - Use Case</p>
<h3 id="referencestitle">1.4 ReferencesTitle</h3>

<table>
<thead>
<tr>
<th>Title</th>
<th>Version Publishing Organization</th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://sweatforsuccess689030981.wordpress.com/"> Sweat for Success Blog</a></td>
<td>Sweat for Success</td>
</tr>
<tr>
<td><a href="https://github.com/ThSilv3r/SweatForSuccess">GitHub</a></td>
<td>Sweat for Success</td>
</tr>
</tbody>
</table><h3 id="overview">1.5 Overview</h3>
<p>The following chapter provides an overview of this project with vision and Overall Use Case Diagram. The third chapter (Requirements Specification) delivers more details about the specific requirements in terms of functionality, usability and design parameters. Finally there is a chapter with supporting information.</p>
<h2 id="overall-description">2. Overall Description</h2>
<h3 id="vision">2.1 Vision</h3>
<p>Our vision is to make an Android-App which you can use to share workout-plans and look at plans from other people. Through an account you will be able to track things like, weight, heart rate and body measurements. You will be able to track your progress of what exercises you have done and how you improved. The workout plans will be categorized, and you can filter<br>
which muscles you want to train. So you won’t lose your motivation you can look for different plans and progresses, of other users to motivate you.</p>
<h3 id="use-case-diagram">2.2 Use Case Diagram</h3>
<p><a href="https://drive.google.com/file/d/1bKyiwrzbFiEMzz4HD5FNjAwqQagxVz_z/view">https://drive.google.com/file/d/1bKyiwrzbFiEMzz4HD5FNjAwqQagxVz_z/view</a></p>
<h3 id="technologies">2.3 Technologies</h3>

<table>
<thead>
<tr>
<th></th>
<th></th>
</tr>
</thead>
<tbody>
<tr>
<td>Backend</td>
<td>Kotlin, Datenbank (TBD)</td>
</tr>
<tr>
<td>Frontend</td>
<td>Java, XML</td>
</tr>
<tr>
<td>IDE</td>
<td>Android Studio</td>
</tr>
<tr>
<td>PM</td>
<td>Github, Azure Dev Ops</td>
</tr>
<tr>
<td>Deployment</td>
<td>TBD</td>
</tr>
<tr>
<td>Testing</td>
<td>JUnit</td>
</tr>
</tbody>
</table><h2 id="specific-requirements">3. Specific Requirements</h2>
<h3 id="functionality">3.1 Functionality</h3>
<p>This section shows the different functions we plan to implement in our App<br>
<em>3.1.1 Posting Workouts</em><br>
This allows you to post your own workout plan<br>
<em>3.1.2 Posting Results</em><br>
This allows you to share your result for a specific workout<br>
<em>3.1.3 Creating an Account</em><br>
User can create their own account and give personal information that can be used for tracking and suggestions.<br>
<em>3.1.4 Logging in</em><br>
This app provides the possibility to log into the account and track progress in the app<br>
<em>3.1.5. Logging out</em><br>
This allows to terminate the app<br>
<em>3.1.6. Getting an overview</em><br>
Provides a basic overview over recent workouts and shows progress. Further you can select a workout from this view<br>
<em>3.1.7. Finding Workouts</em><br>
See workouts from different people and get inspired</p>
<h3 id="usability">3.2 Usability</h3>
<p>The idea is to create a basic, intuitive and simple User Interface that allows users to use the app without much explanation.<br>
<em>3.2.1 Usability Requirement One</em> TBD</p>
<h3 id="reliability">3.3 Reliability</h3>
<p>TBD</p>
<h3 id="performance">3.4 Performance</h3>
<p><em>3.4.1 Capacity</em><br>
The system should be able to manage thousands of requests. Also it should be possible to register as many users as necessary.<br>
<em>3.4.2 Storage</em><br>
Smartphones don’t provide much storage. Therefore, we are aiming to keep the needed storage as small as possible.<br>
<em>3.4.3 App perfomance / Response time</em><br>
To provide the best App perfomance we aim to keep the response time as low as possible. This will make the user experience much better.</p>
<h3 id="supportability">3.5 Supportability</h3>
<p><em>3.5.1 Coding Standards</em><br>
We are going to write the code by using the most common clean code standards. For example, we will name our variables and methods by their functionalities. This will keep the code easy to read by everyone and make further development much easier.<br>
<em>3.5.2 Testing Strategy</em><br>
The application will have a high test coverage and all important functionalities and edge cases should be tested. Further mistakes in the implementation will be discovered instantly and it will be easy to locate the error.</p>
<h3 id="design-constraints">3.6 Design Constraints</h3>
<p>TBD</p>
<h3 id="online-user-documentation-and-help-system-requirements">3.7 Online User Documentation and Help System Requirements</h3>
<p>We will create an FAQ which will be accessible through a help button</p>
<h3 id="purchased-components">3.8 Purchased Components</h3>
<p>N/A</p>
<h3 id="interfaces">3.9 Interfaces</h3>
<p>TBD<br>
<em>3.9.1 User Interfaces</em><br>
• Login - this page is used to log in<br>
• Register - provides a registration form<br>
• Overwiew of personal sessions - shows all the sessions a user participates in • Profile - makes it possible to post information about yourself, might provide    	messaging feature, also shows additional information about users (for   example: Language, country, favorite games, etc.)<br>
<em>3.9.2 Hardware Interfaces</em><br>
N/A<br>
<em>3.9.3 Software Interfaces</em><br>
The app will be runnable on Android 9 and higher. iOS won’t be featured at the moment.<br>
<em>3.9.4 Communications Interfaces</em><br>
TBD</p>
<h3 id="licensing-requirements">3.10 Licensing Requirements</h3>
<h3 id="legal-copyright-and-other-notices">3.11 Legal, Copyright and Other Notices</h3>
<p>We only provide a free service and do not take responsibility for any errors or incorrect data that results from the use of this app</p>
<h3 id="applicable-standards">3.12 Applicable Standards</h3>
<p>TBD</p>
<h2 id="supporting-information">4. Supporting Information</h2>
<p>For any further information you can contact the Sweat for Success Team or check our Sweat for Success Blog. The Team Members are:<br>
• Marina Vollmer<br>
• Jonas Hirsch<br>
• Nils Wendland</p>

  </div>
</body>

</html>
