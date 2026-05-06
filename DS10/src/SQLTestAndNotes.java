import java.sql.*;

public class SQLTestAndNotes
{
    private Connection connection;

    public SQLTestAndNotes() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "SQLPa55w0rd");
        } catch (Exception e) {
            System.out.println(e);
        }
        connection.close();

        //Process for Execution an SQL Command:
        //1. Create a Statement Object:
        Statement statement = connection.createStatement();
        //2. Preform the desired statement and store its result. Statements are broken into 3 categories, and are covered in the following slides:
        //  · Execute - Used to create tables, drop tables, show tables, describe tables, and alter tables.
        //  · ExecuteUpdate - Used to insert, delete or update table rows.
        //  . ExecuteQuery - Used for select statements.

        //execute Method:
        //We will use the execute method for CREATE TABLE, DROP TABLE, and ALTER TABLE.
        //Format:
        //statement.execute("SQL Command");
        //Example:
        statement.execute("DROP TABLE IF EXISTS student");

        //executeUpdate Method:
        //We will use the executeUpdate method for INSERT INTO, DELETE FROM, and UPDATE TABLE. executeUpdate will return the number of rows affected by the statement.
        //Format:
        //int value = statement.executeUpdate("SQL Command");
        //Examples:
        int inserted = statement.executeUpdate("INSERT INTO student (first_name, last_name) VALUES (\'Jason\', \'Tully\')");

        //executeQuery Method:
        //We will use the executeQuery method for SELECT FROM statements.
        //Running an executeQuery method will produce a ResultSet Object storing the resulting rows from the query.
        //Format:
        //ResultSet set = statement.executeUpdate("SQL Command");
        //Examples:
        ResultSet rs = statement.executeQuery("SELECT * FROM student WHERE id >=1;");


        //ResultSet (Navigating):
        //A ResultSet is a group of results returned after a SQL statement runs. You can think of it like a list of rows, where each row contains a list of column values.
        //When a ResultSet is first created, it is positioned before the first row. To move to a row of data, you must call the next() method.
        //The next() method returns a boolean value:

        //Return Value          Meaning
        //true                  The ResultSet successfully moved to a row of data
        //false                 There are no more rows to read

        //ResultSet (Accessing Columns):
        //To access a value from the current row, use the correct getter method for
        //the data type, such as:
        //  · getlnt(column or columnName)
        //  · getString(column or columnName)
        //  · getDouble(column or columnName)
        //Columns can be accessed by their position number, starting at 1, not 0.
        //Examples:
        //  . int id = results.getInt(1);
        //  . String name = results.getString(2);
        //  . int id = results.getInt("student_id");
        //  . String name = results.getString("student_name");

        //ResultSet (Column Count)
        //Format:
        //set.getMetaData().getColumnCount();
        //Example:
        //results.getMetaData().getColumnCount();

        //ResultSet Visual:
        //The ResultSet starts at the position of first, which has null for its data.
        //The next() method is used to advance the position forward once.
        //next() returns true when it advances to a node that is not last.

        //Query & ResultSet Example:
        ResultSet rs1 = statement.executeQuery("SELECT * FROM student WHERE id>=1;");
        while(rs1 != null&&rs.next())
            System.out.println(rs1.getInt("id") + "-"+rs.getString("last_name") +"," + rs.getString("first_name"));
    }
}
