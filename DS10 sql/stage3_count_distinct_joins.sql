USE library_management;

SELECT Books.title, Authors.last_name
FROM Books
JOIN Authors ON Books.author_id = Authors.author_id;

SELECT COUNT(*) FROM Loans;

SELECT COUNT(DISTINCT member_id) FROM Loans;

SELECT DISTINCT shelf_location FROM Books;

SELECT Members.first_name, Loans.loan_id
FROM Members
JOIN Loans ON Members.member_id = Loans.member_id;