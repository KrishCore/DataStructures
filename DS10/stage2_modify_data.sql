USE library_management;
SET SQL_SAFE_UPDATES = 0;

ALTER TABLE Members CHANGE COLUMN email email_address VARCHAR(50);
ALTER TABLE Books DROP genre;
ALTER TABLE Books ADD COLUMN shelf_location VARCHAR(10);
ALTER TABLE Members ADD COLUMN membership_level VARCHAR(20);
ALTER TABLE Loans ADD COLUMN loan_status VARCHAR(15);


-- Membership level
UPDATE Members SET membership_level = 'Premium' WHERE member_id IN (101,103);
UPDATE Members SET membership_level = 'Standard' WHERE member_id IN (102,104);

-- Shelf locations
UPDATE Books SET shelf_location = 'A1' WHERE book_id = 201;
UPDATE Books SET shelf_location = 'B2' WHERE book_id = 202;
UPDATE Books SET shelf_location = 'C3' WHERE book_id = 203;
UPDATE Books SET shelf_location = 'B1' WHERE book_id = 204;
UPDATE Books SET shelf_location = 'D4' WHERE book_id = 205;

-- Updates
UPDATE Members SET last_name = 'Lewis' WHERE member_id = 104;
UPDATE Books SET title = 'Frankenstein; or, The Modern Prometheus' WHERE book_id = 205;
UPDATE Books SET copies_total = 5 WHERE book_id = 204;
UPDATE Books SET copies_available = 3 WHERE book_id = 205;

-- Loan statuesees
UPDATE Loans SET loan_status = 'Returned' WHERE return_date IS NOT NULL;
UPDATE Loans SET loan_status = 'Checked Out' WHERE return_date IS NULL;

-- Deletes
DELETE FROM Loans WHERE loan_id = 302;
DELETE FROM Loans WHERE loan_id = 303;
DELETE FROM Members WHERE member_id = 102;
DELETE FROM Books WHERE book_id = 204;

-- Outputs
DESCRIBE Members;
DESCRIBE Books;
DESCRIBE Loans;

SELECT * FROM Members;
SELECT * FROM Books;
SELECT * FROM Loans;

SELECT * FROM Members WHERE member_id = 102;
SELECT * FROM Books WHERE book_id = 204;

SELECT member_id, membership_level FROM Members;
SELECT book_id, shelf_location FROM Books;
SELECT loan_id, loan_status FROM Loans;
