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
<td>&lt;18/May/2021&gt;</td>
<td>&lt;1.1&gt;</td>
<td>Updated</td>
<td>Nils</td>
<td></td>
</tr>
<tr>
<td>&lt;10/June/2021&gt;</td>
<td>&lt;1.2&gt;</td>
<td>Revision - prep for client handover</td>
<td>Nils</td>
<td></td>
</tr>
 <tr>
<td>&lt;29/June/2021&gt;</td>
<td>&lt;2&gt;</td>
<td>Final Version</td>
<td>Nils</td>
<td></td>
</tr></tbody>
</table><h1 id="toc"></h1>
<h2 id="introduction">1. Introduction</h2>
<h3 id="purpose">1.1 Purpose</h3>
<p>This Software Requirements Specification (SRS) describes all specifications for the application “Sweat for Success”. It includes an overview of this project as well as the vision, detailed information about the planned features and boundary conditions of the development process.</p>
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
<h3 id="referencestitle">1.4 References</h3>

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
<td><a href="https://github.com/ThSilv3r/Sweat4Success">GitHub</a></td>
<td>Sweat for Success</td>
</tr>
</tbody>
</table><h3 id="overview">1.5 Overview</h3>
<p>The following chapter provides an overview of this project with vision and Overall Use Case Diagram. The third chapter (Requirements Specification) delivers more details about the specific requirements in terms of functionality, usability and design parameters. Finally there is a chapter with supporting information.</p>
<h2 id="overall-description">2. Overall Description</h2>
<h3 id="vision">2.1 Vision</h3>
<p>Our vision is to create an Android-App which you can use to share workout plans and get inspiration from other people and their workouts. Through an account you will be able to track different metrics such as weight, heart rate and body measurements. You will be able to track your progress of what exercises you have done and how you improved. The workout plans will be categorized, and you can filter them to find faster what you are looking for and which muscles you want to work on. To ensure you won’t lose motivation you can look for different plans and progress, of other users. Different ways of interacting with other users help create a group atmosphere and helps magnify tmomentum. Remember we're all in this together.</p>
<h3 id="use-case-diagram">2.2 Use Case Diagram</h3>
<p><a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/documents/pictures/Use%20Case%20Diagram%20aktuell.jpeg"></a></p>
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
<td>Kotlin, Room</td>
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
<em>3.1.1 Create an Account</em><br>
User can create their own account and give personal information that can be used for tracking and suggestions.<br>
<em>3.1.2 Log in</em><br>
This app provides the possibility to log into the account and track progress in the app<br>
<em>3.1.3. Log out</em><br>
This allows to terminate the app<br>
<em>3.1.4 Post Workouts</em><br>
This allows you to post your own workout <br>
<em>3.1.5 Post Results</em><br>
This allows you to share your result for a specific workout. This function is not part of the original deployment and will be added later<br>
<em>3.1.6. Get an overview</em><br>
Provides a basic overview over recent workouts and shows progress. Further you can select a workout from this view. This will also be added in the second release.<br>
<em>3.1.7. Finding Workouts</em><br>
See workouts from different people and get inspired</p>
<em>3.1.7. Share Workouts</em><br>
Users are able to share workouts they like with their friends. This creates an incentive to make friends and also work together to reach goals</p>
<h3 id="usability">3.2 Usability</h3>
<p>The idea is to create a basic, intuitive and simple User Interface that allows users to use the app without much explanation. User Interface should encourage users to start their first workout right away and continue using the app. Ease of access should help the user stay motivated. The app shall never be a reason for a user to skip a workout or stop working out entirely.<br>
<em>3.2.1 Usability Requirement One</em> TBD</p>
<h3 id="reliability">3.3 Reliability</h3>
<p>Our app shall be downloadable and useable whereever there is an internet connection. Our goal is to minimize downtimes so that the app neve discourages anyone from working out</p>
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
<p>We want a simple design, the goal is to keep the app intuitive and oriented on the different functionalities. App design should be simple, yet attractive enough to make users want to use the app.
All code will be written in Kotlin and is supported by Android Studio keeping the code easy to maintain and leave the door open to create more functionalities in the future. Another goal is to create a fast app, so speed will trump other factors when in doubt, as longer waiting periods could discourage users from working out or using the app entirely.</p>
<h3 id="online-user-documentation-and-help-system-requirements">3.7 Online User Documentation and Help System Requirements</h3>
<p>We will create an FAQ which will be accessible through a help button</p>
<h3 id="purchased-components">3.8 Purchased Components</h3>
<p>Not applicable</p>
<h3 id="interfaces">3.9 Interfaces</h3>
<p><br>
<em>3.9.1 User Interfaces</em><br>
• Login - this page is used to log in<br>
• Register - provides a registration form<br>
• Overwiew of personal sessions - shows all the sessions a user participates in • Profile - makes it possible to post information about yourself, might provide    	messaging feature, also shows additional information about users (for   example: Language, country, favorite games, etc.)<br>
<em>3.9.2 Hardware Interfaces</em><br>
N/A<br>
<em>3.9.3 Software Interfaces</em><br>
The app will be runnable on Android 9 and higher. iOS won’t be featured at the moment.<br>
<em>3.9.4 Communications Interfaces</em><br>
Not applicable</p>
<h3 id="licensing-requirements">3.10 Licensing Requirements</h3>
<p>We plan to publish the source code under an open-source license.</p>
<h3 id="legal-copyright-and-other-notices">3.11 Legal, Copyright and Other Notices</h3>
<p>We only provide a free service and do not take responsibility for any errors or incorrect data that results from the use of this app</p>
<h3 id="applicable-standards">3.12 Applicable Standards</h3>
<p>The development will follow the common clean code standards and naming conventions. Also, we will create a definition of which will be added here as soon as it's complete.</p>
<h2 id="supporting-information">4. Supporting Information</h2>
<p>For any further information you can contact the Sweat for Success Team or check our Sweat for Success Blog. The Team Members are:<br>
• Marina Vollmer<br>
• Jonas Hirsch<br>
• Nils Wendland</p>
<h2>5. Risk Management</h2>
<p>Here you can find our <a href="https://github.com/ThSilv3r/Sweat4Success/blob/master/documents/Risks.xlsx">Risk Management</a>.</p>

  </div>
</body>

</html>
