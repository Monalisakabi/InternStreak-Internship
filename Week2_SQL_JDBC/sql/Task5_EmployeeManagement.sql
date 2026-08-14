CREATE DATABASE IF NOT EXISTS employee_db;

USE employee_db;

CREATE TABLE IF NOT EXISTS employee (

                                        id INT PRIMARY KEY AUTO_INCREMENT,

                                        name VARCHAR(100) NOT NULL,

    email VARCHAR(100) UNIQUE,

    department VARCHAR(100),

    salary DECIMAL(10,2)

    );


-- Sample data
INSERT INTO employee
(name, email, department, salary)
VALUES
    ('Rahul Kumar', 'rahul@gmail.com', 'IT', 45000),
    ('Priya Das', 'priya@gmail.com', 'HR', 40000),
    ('Amit Sharma', 'amit@gmail.com', 'Finance', 50000);


-- Display employees
SELECT *
FROM employee;


-- GROUP BY
SELECT
    department,
    COUNT(*) AS total_employees
FROM employee
GROUP BY department;


-- HAVING
SELECT
    department,
    COUNT(*) AS total_employees
FROM employee
GROUP BY department
HAVING COUNT(*) >= 2;


-- Highest salary
SELECT MAX(salary) AS highest_salary
FROM employee;


-- Average salary
SELECT AVG(salary) AS average_salary
FROM employee;