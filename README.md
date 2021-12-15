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
- MySQL Workbench 8.0.27
- Spring Tool Suite 4 Version: 4.13.0.RELEASE
- Postman Version 9.4.1

About the Code
- This webapplication is created as a Spring boot application
- Dependencies include Spring Boot, Mysql connector, lombok, hsqldl, maven plugin
- Included the TableCreationScripts to create the necessary schema and table used by the code
- Update the application properties based on the host
- If junits needs to be tested, the application-test properties also requires update

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
  
     
