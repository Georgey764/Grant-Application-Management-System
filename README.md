# Application Submission Software  

> This is a software made for the application submission process at Emerging Scholars.

## Overview
1. <a href="#prerequisites">Prerequisites</a>
2. Tech Stack
3. Setting up the project
      * Setting up the database
      * Setting up the backend server
      * Setting up the frontend
4. Using the Application

## <p id="prerequisites">Prequisites</p>
- **mysql** >= 8.3.0
- **npm** >= 10.5.0 (LTS Recommended)
- **java** 21 (LTS Recommended)
- **Node.js** >= v20.12.1 (LTS Recommended)

## Tech Stack
- Spring Boot
- Spring Security
- React.js
- MySQL

## Setting up the project
I will give you a step by step instructions on how to set up the project. Since this is a full-stack application that uses database the set up is going to be very extensive.

#### First step in setting up the project:
Clone the Repo

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
  
  
3. Restore the dump DatabaseSchemaQueries.sql in /EmergingScholarsApplication/Backend/DatabaseSchemaQueries.sql

   ```
   mysql -u username -p emerging_data < ./DatabaseSchemaQueries.sql
   ```

#### Setting up the backend server
1. Open application.properties file located at /EmergingScholarsApplication/Backend/src/main/resources/application.properties and change the following variable names:
2. Change your directory back to the Backend project folder /EmergingScholarsApplication/Backend/
3. Run the following command

#### Setting up the frontend
1. Change the directory to /EmergingScholarsApplication/Frontend/
2. Run the following command

## Using the Application
