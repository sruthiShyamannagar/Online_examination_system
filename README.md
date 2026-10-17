# Online Examination and Automated Quiz Evaluation Platform

## Project Overview

The **Online Examination and Automated Quiz Evaluation Platform** is a Java-based desktop application developed to simplify the process of conducting online examinations and evaluating objective-type questions automatically.

The application provides separate functionalities for administrators and students. Administrators can manage examinations and questions, while students can register, log in, attempt examinations, and view their results. The system uses MySQL to store and manage examination-related information.

## Objectives

- To develop a user-friendly examination management system.
- To maintain student, examination, question, and result records in a database.
- To automate the evaluation of objective-type questions.
- To reduce manual effort in calculating examination scores.
- To demonstrate database connectivity and relational database concepts.

## Features

- Student registration and login
- Administrator registration and login
- Examination and question management
- Multiple-choice question evaluation
- Automatic score calculation
- Result storage and retrieval
- MySQL database integration
- Java Swing graphical user interface

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| Java Swing | Graphical user interface |
| MySQL | Database management |
| JDBC | Java-to-MySQL connectivity |
| Apache Maven | Dependency and project management |
| Git and GitHub | Version control and source-code hosting |

## Project Structure

```text
Online_examination_system/
├── database/
│   └── examination.sql
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── onlineexam/
│                   ├── dao/
│                   ├── gui/
│                   ├── model/
│                   ├── util/
│                   └── Main.java
├── .gitignore
├── pom.xml
└── README.md
```

## Database Setup

1. Install MySQL Server and MySQL Workbench.
2. Open MySQL Workbench and connect to your MySQL server.
3. Open the `database/examination.sql` file from this repository.
4. Execute the SQL script to create the database and tables.
5. Open `src/main/java/com/onlineexam/util/DBConnection.java`.
6. Configure the database URL, username, and password for your local MySQL installation.

**Security note:** Do not upload real database passwords or other credentials to GitHub.

## How to Run the Project

### Prerequisites

- Java Development Kit (JDK)
- Apache Maven
- MySQL Server
- An IDE such as Visual Studio Code, IntelliJ IDEA, or Eclipse

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/sruthiShyamannagar/Online_examination_system.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Online_examination_system
   ```

3. Configure the MySQL database connection in `DBConnection.java`.

4. Compile the project:

   ```bash
   mvn clean compile
   ```

5. Run the application using your IDE, or use the configured Maven execution command if one is available in the project.

The application entry point is `src/main/java/com/onlineexam/Main.java`.

## Database Management System Concepts

This project demonstrates the practical application of:

- Relational database design
- Tables, primary keys, and foreign keys
- SQL queries and data manipulation
- Database connectivity using JDBC
- Data integrity and relationships
- Persistent storage and retrieval of examination records

## Future Enhancements

- Examination timer and automatic submission
- Randomized question selection
- Negative marking
- Detailed performance reports
- Password recovery
- Enhanced security and audit logging

## Author

**Name:** Sruthi Shyamannagar

**Project:** Online Examination and Automated Quiz Evaluation Platform

**Purpose:** College academic project for Database Management Systems (DBMS).

## Repository

[View the project on GitHub](https://github.com/sruthiShyamannagar/Online_examination_system)

