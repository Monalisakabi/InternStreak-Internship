CREATE DATABASE IF NOT EXISTS week2_db;

USE week2_db;

-- Department table
CREATE TABLE IF NOT EXISTS department (
                                          dept_id INT PRIMARY KEY AUTO_INCREMENT,
                                          dept_name VARCHAR(100) NOT NULL UNIQUE
);

-- Student table
CREATE TABLE IF NOT EXISTS student (
                                       student_id INT PRIMARY KEY AUTO_INCREMENT,
                                       student_name VARCHAR(100) NOT NULL,
                                       email VARCHAR(100) UNIQUE,
                                       phone VARCHAR(15),
                                       dept_id INT,

                                       CONSTRAINT fk_student_department
                                           FOREIGN KEY (dept_id)
                                               REFERENCES department(dept_id)
);

-- Insert department data
INSERT INTO department (dept_name)
VALUES
    ('Computer Science'),
    ('Information Technology'),
    ('Electronics'),
    ('Management');

-- Insert student data
INSERT INTO student
(student_name, email, phone, dept_id)
VALUES
    ('Rahul Kumar', 'rahul@gmail.com', '9876543210', 1),
    ('Priya Das', 'priya@gmail.com', '9876543211', 2),
    ('Amit Sharma', 'amit@gmail.com', '9876543212', 1),
    ('Sneha Patel', 'sneha@gmail.com', '9876543213', 3);

-- Display departments
SELECT * FROM department;

-- Display students
SELECT * FROM student;