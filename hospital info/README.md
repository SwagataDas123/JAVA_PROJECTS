## Hospital Information System

### Overview

The Hospital Information System is a Java-based application that helps manage doctors, patients, and medical observations efficiently. It allows hospitals to register doctors and patients, assign doctors to patients, store patient observations, and retrieve or update records. The system uses MySQL as the database backend and JDBC for database connectivity.

## Features

✅ Register new doctors and patients

✅ Assign doctors to patients

✅ Store and retrieve patient medical observations

✅ View patient details

✅ Update doctor and patient records

✅ Delete patient records

✅ Uses JDBC for database operations

## Technologies Used

Java (JDK 8 or later)

JDBC (Java Database Connectivity)

MySQL (Relational Database Management System)

Scanner Class (for user input handling)

I did this in BlueJ

## Database Schema

The system has 3 tables : Doctors, Patients, and Observations.

Doctors:

 CREATE TABLE Doctors (
    ->     id INT PRIMARY KEY AUTO_INCREMENT,
    ->     name VARCHAR(255) NOT NULL,
    ->     specialization VARCHAR(255) NOT NULL
    -> );

Patients:

CREATE TABLE Patients (
    ->     id INT PRIMARY KEY AUTO_INCREMENT,
    ->     name VARCHAR(255) NOT NULL,
    ->     age INT NOT NULL,
    ->     case_of_injury TEXT NOT NULL,
    ->     doctor_assigned_id INT,
    ->     FOREIGN KEY (doctor_assigned_id) REFERENCES Doctors(id) ON DELETE SET NULL
    -> );

Observations:

CREATE TABLE Observations (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     patient_id INT NOT NULL,
    ->     type VARCHAR(255) NOT NULL,
    ->     value TEXT NOT NULL,
    ->     timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ->     FOREIGN KEY (patient_id) REFERENCES Patients(id) ON DELETE CASCADE
    -> );

## Installation

Clone the repository

Set up and use the database in MySql CLI or Workbench:

CREATE DATABASE hospital_db;

USE hospital_db;

Update the database credentials in DatabaseHelper.java with your password and username.

Run the program to automatically create tables.

Compile and Run the Project:

javac *.java

java hospital_info_system

## Flow of Project 

The **Hospital Information System** consists of three main classes: `Doctor`, `Patient`, and `hospital_management`, along with a `DatabaseHelper` class for database interactions. The `Doctor` and `Patient` classes represent individual doctors and patients, storing attributes like name, ID, specialization (for doctors), and case of injury (for patients). The `hospital_management` class acts as a controller, handling operations like registering doctors and patients, assigning doctors to patients, adding observations, and retrieving or updating data. These methods internally call corresponding functions in the `DatabaseHelper` class, which is responsible for executing SQL queries using JDBC. The program starts in `hospital_info_system.java`, where it initializes the database, accepts user input for doctors and patients, and provides an interactive menu for managing the system. The user can add, update, delete, or retrieve records, and all data is stored in a **MySQL database**. The **flow of execution** follows: `hospital_info_system` → `hospital_management` → `DatabaseHelper`, ensuring modular and structured data handling.














