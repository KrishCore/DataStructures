-- @block
DROP DATABASE IF EXISTS library_management;
CREATE DATABASE library_management;
USE library_management;

-- DROP TABLE Loans;
-- DROP TABLE Books;
-- DROP TABLE Members;
-- DROP TABLE Authors;

-- @block
CREATE TABLE Authors (
	author_id INT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    birth_year INT
);
-- @block
CREATE TABLE Members (
	member_id INT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50),
    join_date DATE
);
-- @block
CREATE TABLE Books (
	book_id INT PRIMARY KEY,
	title VARCHAR(60) NOT NULL,
	author_id INT,
	genre VARCHAR(30),
	publish_year INT,
	copies_total INT,
	copies_available INT,
    FOREIGN KEY (author_id) REFERENCES Authors(author_id)
);
-- @block
CREATE TABLE Loans (
	loan_id INT PRIMARY KEY,
	member_id INT,
    book_id INT,
    loan_date DATE,
    due_date DATE,
    return_date DATE,
    FOREIGN KEY (member_id) REFERENCES Members(member_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

-- @block
INSERT INTO Authors VALUES
(1, 'George', 'Orwell', 1903),
(2,	'Harper', 'Lee', 1926),
(3,	'Jane', 'Austen', 1775),
(4,	'F. Scott', 'Fitzgerald', 1896),
(5,	'Mary', 'Shelley', 1797);

-- @block
INSERT INTO Members VALUES
(101, 'Alice', 'Johnson', 'alice.johnson@email.com', '2024-01-15'),
(102, 'Brian', 'Smith', 'brian.smith@email.com', '2024-02-10'),
(103, 'Carla', 'Gomez', 'carla.gomez@email.com', '2024-03-05'),
(104, 'David', 'Lee', 'david.lee@email.com', '2024-03-20');

-- @block
INSERT INTO Books VALUES
(201, '1984', 1, 'Dystopian', 1949, 5, 3),
(202, 'To Kill a Mockingbird', 2, 'Fiction', 1960, 4, 2),
(203, 'Pride and Prejudice', 3, 'Romance', 1813, 6, 4),
(204, 'The Great Gatsby', 4, 'Fiction', 1925, 3, 1),
(205, 'Frankenstein', 5, 'Horror', 1818, 2, 2);

-- @block
INSERT INTO Loans VALUES
(301, 101, 201, '2026-01-10', '2026-01-24', '2026-01-20'),
(302, 102, 202, '2026-02-01', '2026-02-15', NULL),
(303, 103, 204, '2026-02-08', '2026-02-22', NULL),
(304, 101, 203,	'2026-02-11', '2026-02-25', '2026-02-18');

-- @block
SELECT * FROM Authors;
SELECT * FROM Members;
SELECT * FROM Books;
SELECT * FROM Loans;

SELECT COUNT(*) FROM Authors;
SELECT COUNT(*) FROM Members;
SELECT COUNT(*) FROM Books;
SELECT COUNT(*) FROM Loans;

SELECT Books.title, Authors.last_name
FROM Books
JOIN Authors ON Books.author_id = Authors.author_id;

SELECT loan_id, member_id, book_id
FROM Loans;
