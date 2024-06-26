# Application Submission Software  

> This is a software made for the application submission process at Emerging Scholars.

## Overview
1. <a href="#walkthrough">Walkthrough Video</a>
2. <a href="#screenshots">Screenshots</a>
3. <a href="#prerequisites">Prerequisites</a>
4. <a href="#tech-stack">Tech Stack</a>
5. <a href="#setting-up-project">Setting up the project</a>
      * Setting up the database
      * Setting up the backend server
      * Setting up the frontend
6. <a href="#using-app">Demo Accounts for Using the Application</a>

## <p id="walkthrough">Walkthrough Video</p>

## <p id="screenshots">Screenshots</p>
1. Sign-in and Sign-up page

     > The sign-in and sign-up feature is fully made from scratch using Spring Security. It is robust and uses secure security practices.
   <div align="center">
        <img width="433" alt="Screenshot 2024-06-26 at 7 58 47 AM" src="https://github.com/Georgey764/applications-submission-software/assets/127057827/1ae7d9bf-6f4d-4303-836b-a0b19ae86c18">
        <img width="500" alt="Screenshot 2024-06-26 at 7 59 45 AM" src="https://github.com/Georgey764/applications-submission-software/assets/127057827/86c26ef6-a9d1-4cbe-9f2c-d4d17c8f9b15">  
   </div>

3. Student homepage
     > Students can view application status, sent applications and professors with available projects.
     <img width="550" alt="Screenshot 2024-06-26 at 8 00 36 AM" src="https://github.com/Georgey764/applications-submission-software/assets/127057827/6276b2a7-6468-4f5b-b76c-dd3fc8de8dab">

   
4. Student form submission page
     > Students can send their applications with their information and resume to the professors.
     <img width="550" alt="Screenshot 2024-06-26 at 8 01 03 AM" src="https://github.com/Georgey764/applications-submission-software/assets/127057827/09ee3fbf-e732-4d5e-b7cf-05338285c349">
     
   
5. Faculty projects page
   > Faculties can create the project description that will be displayed to the students in the homepage.
    <img width="550" alt="Screenshot 2024-06-26 at 8 01 54 AM" src="https://github.com/Georgey764/applications-submission-software/assets/127057827/91d48b3f-d04d-4291-8cc3-3c87c4b84ace">

6. Faculty view submissions page
     > Faculties can view the student application and give a decision in the view applications page.
     <img width="550" alt="Screenshot 2024-06-26 at 8 02 28 AM" src="https://github.com/Georgey764/applications-submission-software/assets/127057827/b2a8132a-e00b-4774-8b16-6e08675a8a1a">

## <p id="prerequisites">Prequisites</p>
- **mysql** >= 8.3.0
- **npm** >= 10.5.0 (LTS Recommended)
- **java** 21 (LTS Recommended)
- **Node.js** >= v20.12.1 (LTS Recommended)

## <p id="tech-stack">Tech Stack</p>
- Spring Boot
- Spring Security
- React.js
- MySQL

## <p id="setting-up-project">Setting up the project</p>
I will give you a step by step instructions on how to set up the project. Since this is a full-stack application that uses database the set up is going to be very extensive.

#### First step in setting up the project:
- Open the command line interface and then clone the Repo:

     ```
     git clone https://github.com/Georgey764/applications-submission-software.git
     cd applications-submission-software
     ```
  
#### Setting up the database  
1. Start the mysql server  
  
     _**In Mac OS**_
     ```
     mysql.server start
     ```
      _**In Windows**_
     ```
     mysqld
     ```
2. Create a database called 'emerging_data'

   ```
   mysql -u username -p -e "CREATE DATABASE emerging_data;"
   ```
   > You will be prompted to enter password. If your mysql doesn't have any password you can omit the -p
   > username = your mysql username
  
  
3. Restore the dump DatabaseSchemaQueries.sql in /applications-submission-software/Backend/DatabaseSchemaQueries.sql

   ```
   mysql -u username -p emerging_data < ./DatabaseSchemaQueries.sql
   ```

#### Setting up the backend server
1. Open application.properties file located at **/applications-submission-software/Backend/src/main/resources/application.properties** and change the following variable names:
  
     ```
     #Change the values of the following variables to your mysql user details
     spring.datasource.url=${DB_URL}
     spring.datasource.username=${DB_USERNAME}
     
     #Un-comment the following line if your mysql account has a password
     #spring.datasource.password=${DB_PASSWORD}
     ```
  
2. Change your directory back to the Backend project folder /applications-submission-software/Backend/

     ```
     cd /applications-submission-software/Backend/
     ```

3. Set the environment variables

     _**In MacOS**_
     ```
     source env_vars.txt
     ```

     _**In Windows**_
     ```
     for /f "delims=" %i in (env_vars.txt) do set %i
     ```
  
5. Run the following command
  
     ```
     ./mvnw spring-boot:run
     ```
  
#### Setting up the frontend
1. Change the directory to /applications-submission-software/Frontend/

     ```
     cd /applications-submission-software/Frontend/
     ```
  
2. Install the required dependencies
  
     ```
     npm install
     ```
  
3. Run the following command
  
     ```
     npm start
     ```
  
## <p id="using-app">Using the Application</p>

Here is a list of demo accounts you can use to try out the application after you set it up.

#### Demo Accounts:  
     Student: 
     email: johndoe@warhawks.ulm.edu, nelson@warhawks.ulm.edu, blue@warhawks.ulm.edu, green@warhawks.ulm.edu  
     password: password!123  

     Teacher/Faculty:
     email: janedoe@ulm.edu, welsh@ulm.edu, michaels@ulm.edu, margaret@ulm.edu
     password: password!123
