# RobWarehouse

This project built using Java and the following tools:

Spring Boot as server side framework
Maven as build automation tool
MySQL as database implementation
Spring Data JPA as the top layer over Hibernate
Thymeleaf as template engine 

# Model
Domain model is organized under the model package and it consists of entity classes. Entities use various annotations that describe the relationships between each other.

1. Create a MySQL database
CREATE DATABASE warehouse;
In case you want to use a different database name, follow the next steps:

# CREATE DATABASE DB_NAME;
Open src/main/resources/application.properties file
Change db.name property to match your preferred database name DB_NAME
2. Modify MySQL username and password
Open src/main/resources/application.properties file
Change spring.datasource.username and spring.datasource.password properties to match your MySQL connection
