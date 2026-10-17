# Online Examination and Automated Quiz Evaluation Platform

Java Swing + MySQL + JDBC college project.

## Features
- Student registration and login
- Admin login
- Admin creates exams
- Admin adds MCQ questions
- Student views available exams
- Timed examination
- Next/Previous navigation
- Automatic answer evaluation
- Result and percentage
- MySQL persistence

## Requirements
- JDK 17+
- MySQL 8+
- Maven 3.9+
- VS Code or IntelliJ IDEA

## Setup
1. Create the database by running `database/examination.sql` in MySQL Workbench.
2. Open `src/main/java/com/onlineexam/util/DBConnection.java`.
3. Change `YOUR_MYSQL_PASSWORD` to your MySQL root password.
4. From the project folder run:
   `mvn clean compile`
5. Run:
   `mvn exec:java`
6. Admin login:
   username: admin
   password: admin123
7. Register a student and take the sample Java exam.

## Important
For a real deployment, passwords should be hashed. This educational version keeps the authentication simple so the JDBC/database flow is easy to understand for a college presentation.
