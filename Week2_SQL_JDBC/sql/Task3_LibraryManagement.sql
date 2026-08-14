CREATE DATABASE IF NOT EXISTS library_db;

USE library_db;

-- Books table
CREATE TABLE IF NOT EXISTS books (
                                     book_id INT PRIMARY KEY AUTO_INCREMENT,
                                     title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(100),
    price DECIMAL(10,2),
    quantity INT DEFAULT 0
    );

-- Issue book table
CREATE TABLE IF NOT EXISTS issue_book (
                                          issue_id INT PRIMARY KEY AUTO_INCREMENT,
                                          book_id INT NOT NULL,
                                          student_name VARCHAR(100) NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) DEFAULT 'ISSUED',

    CONSTRAINT fk_issue_book
    FOREIGN KEY (book_id)
    REFERENCES books(book_id)
    );

-- Sample books
INSERT INTO books
(title, author, category, price, quantity)
VALUES
    ('Java Programming', 'Herbert Schildt', 'Programming', 650.00, 5),
    ('Effective Java', 'Joshua Bloch', 'Programming', 750.00, 3),
    ('Database System Concepts', 'Korth', 'Database', 800.00, 4),
    ('HTML and CSS', 'Jon Duckett', 'Web Development', 500.00, 6),
    ('Python Basics', 'Mark Lutz', 'Programming', 700.00, 5);

-- Display books
SELECT * FROM books;

-- Display issue records
SELECT * FROM issue_book;

-- Search by category
SELECT *
FROM books
WHERE category = 'Programming';

-- Group by category
SELECT
    category,
    COUNT(*) AS total_books
FROM books
GROUP BY category;

-- HAVING
SELECT
    category,
    COUNT(*) AS total_books
FROM books
GROUP BY category
HAVING COUNT(*) >= 2;

-- Sort by price
SELECT *
FROM books
ORDER BY price DESC;