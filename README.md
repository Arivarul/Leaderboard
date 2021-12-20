# LeaderBoard
Basic Leaderboard functionality that lists Users
- User is listed by points
- Each user information includes Name, Age, Points
- Board supports increment/decrement of points
- Board also supports addition and removal of users

Pre-requisite
- openjdk (11 or above), I used version "17.0.1" 
- Apache Maven (12.0.1)
- Git version 2.30.1
- MySQL Ver 8.0.27
- Docker version 20.10.11 

For Development & Testing
- MySQL Workbench 8.0.27
- STS: Spring Tool Suite 4 Version: 4.13.0.RELEASE
- Postman Version 9.4.1
- Docker Desktop 4.3.1

About the Code
- This webapplication is created as a Spring boot application
- Dependencies include Spring Boot, Mysql connector, lombok, hsqldl, maven plugin
- Included the TableCreationScripts to create the necessary schema and table used by the code
- Update the application properties based on the host
- If junits needs to be tested, the application-test properties also requires update

Running the Code
- Once the Pre-requisite applications are installed
- We can create the schema as required and run the TableCreationScript (Edit the script based on schema)
- Update the application and test properties for port number and schema connections
- Load the application and run maven verify and build
- In local, we can load this application in an IDE (I used STS) and start the service
- You can use Postman or Browser to hit the URL and test the scenarios using End-points below

Running the Code using Docker
- We can run the service and db server in separate docker containers
- Mysql Docker Image and Container creation
  1. Use the below commands to create Mysql Image (Use platform - only if mac OS is used)
  ```
  docker pull --platform linux/x86_64 mysql --lower_case_table_names=1
  docker pull mysql:latest
  ```
  2. Use the below command to create the Mysql Container
  ```
  docker run --name <MYSQL-CONTAINER-NAME> -e MYSQL_ROOT_PASSWORD=<ROOT_PASSWORD> -e MYSQL_DATABASE=leaderboard -e MYSQL_USER=<USER_NAME> -e MYSQL_PASSWORD=<PASSWOR> -e EXTRA_OPTS="lower_case_table_names=1" -d mysql:latest 
  ```
  3. Use the below command to copy the TableCreationScrips.sql
  ```
  docker cp ../leaderboard/TableCreationScripts.sql leaderboard-mysql:home
  ```
  4. Login to the Mysql container and run the script using below command
  ```
  docker exec -it leaderboard-mysql bin/bash
  mysql -u root -p
  USE leaderboard;
  source /home/TableCreationScripts.sql
  ```
- Service Docker Image and Container creation
  1. Update the application.properties with MySql container information
  2. Run the maven build to create the service jar (leaderboard-latest.jar)
  3. Update the source and destination path to Add the Jar in Dockerfile 
  4. User the below command to build the Docker Image
     ```
     docker build ../leaderboard -t leaderboard:latest
     ```
  5. Build the container using below command linking Mysql container
     ```
     docker run -p 8081:8081 --name leaderboard-docker --link leaderboard-mysql:mysql -d leaderboard
     ```
REST End points handled:
- Please replace localhost and port with the specific server details if hosted online
- Loading Home Page: Fetches all the Users
```
URL: GET http://localhost:8081/leaderboard/home 
Resonse: 
  200 OK : the list of Users sorted by points, returns List of User Object
  204 NO_CONTENT : if no Users are present
```
- Load User Information: Gets the specific User information
```
URL: GET http://localhost:8081/leaderboard/loadUser/{userId}
Response: 
  302 FOUND : If User Object is identified and returned
  404 NOT_FOUND : If User with the "userId" couldn't be found
```
- Increment User Point
```
URL: PUT http://localhost:8081/leaderboard/increment?userId={userId}
Response:
  200 OK : If User point is successfully incremented, returns User object
  417 EXPECTATION_FAILED : If the User exists, but points couldn't be updated successfully
  404 NOT_FOUND : If the User with the "userId" doesn't exist
```
- Decrement User Point
```
URL: PUT http://localhost:8081/leaderboard/decrement?userId={userId}
Response:
  200 OK : If User point is successfully decremented, returns User object
  417 EXPECTATION_FAILED : If the User exists, but points couldn't be updated successfully
  404 NOT_FOUND : If the User with the "userId" doesn't exist
```
- Delete a User
```
URL: DELETE http://localhost:8081/leaderboard/deleteUser?userId={userId}
Response:
  200 OK : Boolean 1 if User is successfully Deleted
  417 EXPECTATION_FAILED : If the User does not exist or deletion was unsuccessfull
```
- Adding a new User. (Shared a sample POST Object below)
```
URL: POST http://localhost:8081/leaderboard/addUser
    {
      "name": "Harry Potter",
      "age": 22,
      "points": 0,
      "address": "No 4 Pivet Drive, Little Whinging, Surrey"
    } 
Response:
  400 BAD_REQUEST : If the User object is missing
  202 ACCEPTED : If User is successfully added
  417 EXPECTATION_FAILED : If the User addition was unsuccessfull
```
  
     
