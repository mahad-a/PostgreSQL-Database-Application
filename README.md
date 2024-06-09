# PostgreSQL Database Application

## Description
Implement a PostgreSQL database using the provided schema and write an application in your language of choice that
connects to this database to perform specific CRUD (Create, Read, Update, Delete) operations.

## Prerequisites

- IDE capable of Java and Maven
- PostgreSQL database server
- Java Database Connectivity driver for PostgreSQL: https://jdbc.postgresql.org/download/

## Setup

1. Clone this repository to your local machine.
2. Make sure you have PostgreSQL installed and running. If you do not have, can be found here, version used is 15.5: https://www.postgresql.org/download/
3. Ensure a database is created or already exists with populated tables. Navigate to your database credentials and locate your server's host,
desired database name, and port.
4. Update the database credentials in the `PostgresSQLJDBCConnection.java` file to match your PostgreSQL database
configuration. This can be found in Main, under url, username and password.
5. Build the project using Maven and input the following dependency into pom.xml
```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```
## Usage

- Run the `PostgresSQLJDBCConnection.java` file to connect to the PostgreSQL database and perform CRUD operations.
- Terminal will output results of CRUD operations, comment out operations if undesired.

