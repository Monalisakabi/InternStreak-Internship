USE week2_db;

-- 1. Display all students
SELECT *
FROM student;


-- 2. Search student using WHERE
SELECT *
FROM student
WHERE student_name = 'Rahul Kumar';


-- 3. Search students by department
SELECT *
FROM student
WHERE dept_id = 1;


-- 4. Sort students
SELECT *
FROM student
ORDER BY student_name ASC;


-- 5. JOIN department and student
SELECT
    s.student_id,
    s.student_name,
    s.email,
    d.dept_name
FROM student s
         INNER JOIN department d
                    ON s.dept_id = d.dept_id;


-- 6. GROUP BY department
SELECT
    d.dept_name,
    COUNT(s.student_id) AS total_students
FROM department d
         LEFT JOIN student s
                   ON d.dept_id = s.dept_id
GROUP BY d.dept_id, d.dept_name;


-- 7. HAVING clause
SELECT
    d.dept_name,
    COUNT(s.student_id) AS total_students
FROM department d LEFT JOIN student s
                   ON d.dept_id = s.dept_id
GROUP BY d.dept_id, d.dept_name
HAVING COUNT(s.student_id) >= 2;


-- 8. Create index on email
CREATE INDEX idx_student_email
    ON student(email);


-- 9. Create index on department
CREATE INDEX idx_student_department
    ON student(dept_id);


-- 10. Display indexes
SHOW INDEX FROM student;


-- 11. Query execution plan
EXPLAIN
SELECT *
FROM student
WHERE email = 'rahul@gmail.com';