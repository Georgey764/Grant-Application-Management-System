# Application Submission Software  

> This is a software made for the application submission process at Emerging Scholars.

## Overview
1. <a href="#prerequisites">Prerequisites</a>
2. <a href="#tech-stack">Tech Stack</a>
3. <a href="#setting-up-project">Setting up the project</a>
      * Setting up the database
      * Setting up the backend server
      * Setting up the frontend
4. <a href="#using-app">Using the Application</a>

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
     git clone https://github.com/Georgey764/EmergingApp.git
     cd EmergingApp
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
  
  
3. Restore the dump DatabaseSchemaQueries.sql in /EmergingApp/Backend/DatabaseSchemaQueries.sql

   ```
   mysql -u username -p emerging_data < ./DatabaseSchemaQueries.sql
   ```

#### Setting up the backend server
1. Open application.properties file located at **/EmergingApp/Backend/src/main/resources/application.properties** and change the following variable names:
  
     ```
     #Change the values of the following variables to your mysql user details
     spring.datasource.url=${DB_URL}
     spring.datasource.username=${DB_USERNAME}
     
     #Un-comment the following line if your mysql account has a password
     #spring.datasource.password=${DB_PASSWORD}
     ```
  
2. Change your directory back to the Backend project folder /EmergingApp/Backend/

     ```
     cd /EmergingApp/Backend/
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
1. Change the directory to /EmergingApp/Frontend/

     ```
     cd /EmergingApp/Frontend/
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
