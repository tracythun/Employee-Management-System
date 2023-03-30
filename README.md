# Employee-Management-System

Employee Salary Management System has these working functions:
* will be able to view employee list (id, Name, Login and Salary) 
* when sorting by name in ascending order, it should display in ascending order by name
* when sorting by salary in ascending order, it should display in ascending order by salary
* when sorting by login in ascending order, it should display in descending order by login
* when clicked on edit function, should be able to edit employee details and modified information will be able to see
* when clicked on delete button, a confirmation prompt will ask if you really want to delete the employee.

<h2>Front end</h2> done by dependencies: 
- Angular CLI: 15.0.5, 
- Node: 16.17.0, 
- Package Manager: npm 8.15.0. 

<h2>Backend</h2> done by Springboot, and MySQL Workbench database( 8.0.32 (Homebrew) version ).

<br/>
<hr>
<h2>LIVE DEMO</h2>
<img width="1438" alt="image" src="https://user-images.githubusercontent.com/67495989/228755620-07c34007-c96a-4741-961b-70f70ec472c0.png">

<h2>SET UP</h2>
<h3>BACK END PART</h3>
On Editor (recommend Spring Tool Suite), go to directory "src/main/resources/application.properties" file.

* Need to change to username and password of local database. 

```sh
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/employee?createDatabaseIfNotExist=true

spring.datasource.username='PUT LOCAL DB USERNAME HERE'
spring.datasource.password=' PUT LOCAL DB PASSWORD HERE'
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jackson.serialization.fail-on-empty-beans=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true

```

* right click "Run As" and choose "Spring Boot App".
<img width="583" alt="image" src="https://user-images.githubusercontent.com/67495989/228762439-5439831c-aeaa-4b58-a0e1-7075495500e0.png">

* After running the app, in MySQL Workbench local database, employee table will be created.
* The table will be empty initially, and data can be filled either from database or through create API (will be explained later at the bottom) 

<h3>FRONT END PART</h3>

After downloading code zip file or cloning, navigate to Angular frontend folder in Visual Studio Code (or any other editors).

## Then install dependencies
In terminal - command

```sh
#install dependencies
npm install 

#serve frontend
ng serve
```
It will run on localhost:4200 by default.

<hr>

<h2>Unit Testing on Spring boot</h2>
* Employee Service Test Class is in the directory "src/test/java/com.example.empmanagement/". To run the junit testing, right click on app name
and click on "Run As"-> "JUnit Test".
<img width="580" alt="image" src="https://user-images.githubusercontent.com/67495989/228766277-e5a5df22-bdef-4653-85fa-9ca531fd200f.png">

<h4>

<h2>Testing APIs</h2>
Recommend using Postman to test the APIs. Status response will be 200 OK when the APIs are working.

* GET Employees- localhost:8081/users (GET)
* GET Employees by Id- localhost:8081/users/{id} (GET)
* ADD Employee by Id- localhost:8081/users/{id} (POST) 

 <b> Click Body->raw->JSON and in the body </b>
  ```
    {
      "login":"test",
      "name":"test tr",
      "salary":3999.999
    }
  ```
 * UPDATE Employee by Id- localhost:8081/users/{id} (PATCH)
 
 <b> Click Body->raw->JSON and in the body </b>
 ```
  {
    "login": "hpotter",
    "name": "Harry Potter",
    "salary": 1233.00
  }
 ```
 * DELETE Employee by Id- localhost:8081/users/{id} (DELETE)
 <h5> E.g. localhost:8081/users/e0001 </h5>
 
 * UPLOAD CSV- localhost:8081/users/upload (POST)
 <b>Click Body->binary->"Select File"
 Then in the Headers, change the information as follows.
<p>
 <img width="613" alt="image" src="https://user-images.githubusercontent.com/67495989/228772947-93cd7afc-92d7-4640-915a-897b9ca92140.png">
</p>
 



