USE library_management;
ALTER TABLE Members CHANGE COLUMN email email_address VARCHAR(50);
ALTER TABLE Books DROP genre;
ALTER TABLE Books ADD COLUMN shelf_location VARCHAR(2);
ALTER TABLE Members ADD COLUMN membership_level VARCHAR(8);
-- ALTER TABLE Loans ADD COLUMN loan_satus ; -- find what variable it is
