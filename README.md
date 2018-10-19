### Project 0 - Java CLI Banking Application
This application allows users to create a bank account and interact with that account through the command line. Users are able to deposit, withdraw, and check funds in their account. The application includes validation for usernames, emails, and more. Users are able to log in to and out of their accounts, maintaining session state via file IO and the singleton design pattern.

### Project 1 - Full-Stack Java Employee Reimbursement Application
This project is a CRUD application for requesting, viewing, approving, and denying financial reimbursements. Within this project, there are two separate user roles: manager and employee. The project uses SQL and JDBC for data persistence uses the DAO design pattern extensively.

Employees can request to be reimbursed for their expenses by submitting a request to a manager. They are able to view all requests that they've made: pending, approved, and denied. Additionally, employees can view their own profile and edit their profile information (e.g., name).

Managers can approve, deny, and view all reimbursement requests based on status and employee name.

Both employees and managers have their own separate views based on account type. These sessions are managed via the use of the Java Servlet HttpSession interface in conjunction with AJAX to display the appropriate data. This is aided by the use of Java Servlets that display specific views based on session state, user role, and endpoint accessed.

### Project 2 - Music Based Social Network (MBSN): Full-Stack Spring with Angular and a CI/CD Pipeline
MBSN is a social network that uses the Spotify Web API to connect users based on common interests in music. Users can create an account, log in, log out, edit their profiles, search for songs and add them to their profile, view their profile page, view a timeline of other users' activity, and see common musical interests.

My primary roles in this project involved working extensively with both our client-side application and DevOps.

For **DevOps**, I handled the creation and maintenance of continuous integration and deployment of our client-side and server-side applications through the use of Codeship and Jenkins respectively, in conjunction with Amazon Web Services (S3, EC2, RDS, and more). Through the use of Jenkins and Codeship pipelines, I was able to automatically deploy our application on successful git merges to the master branch.

For the **client-side/front-end** of the application, I styled most pages of the applications using CSS, and handled tasks such as API/HTTP calls throughout the application using a combination of HTML, Angular, and TypeScript. I also developed much of the structure of the application and wrote the logic for various databinding and DOM manipulation tasks.

Although I was less involved with **server-side** technologies for this project than with DevOps and front-end, I did assist building and customizing certain functionality for the Spring controllers.

Java/Back end: https://github.com/alexbumpers/project2-java-ANM
Angular/Front-end: https://github.com/alexbumpers/project2-angular-ANM

### Project 3 - Revature Rideforce: Ridesharing Application
Rideforce is a ride sharing application that allows users to find rides to work using the Google Maps API.

I was one of three team leads for this project, and in my role, I managed the **DevOps** team.

My duties for the Rideforce project included:
- Managing a DevOps team and delegating tasks to these team members.
- Implementing, creating, and managing a collection of Docker containers which allowed us to separate and isolate all of our various microservices.
- Implementing a Jenkins pipeline for the continuous deployment of our microservices.
- Managing RDS, EC2, and S3 instances using Amazon Web Services.
- Creating a separate development environment in order to isolate development from production. This environment also included a continuous deployment pipeline.
- Automating testing with the use of quality gates in order to ensure that code with failing tests or other similar issues would not be deployed to production.
- Automating code quality and test coverage management through the use of SonarCloud/SonarQube and Jacoco, respectively.
- Generating documentation using Javadocs and Compodoc, and automated the hosting of this documentation via Amazon S3.