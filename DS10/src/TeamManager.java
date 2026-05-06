import java.sql.*;
import java.util.Scanner;

public class TeamManager
{
    private Connection connection;

    public TeamManager() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "SQLPa55w0rd");
        } catch (Exception e) {
            System.out.println(e);
        }
        Statement statement = connection.createStatement();

        statement.execute("DROP TABLE team");
        statement.execute("CREATE TABLE IF NOT EXISTS team (" +
                "team_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "team_name VARCHAR(50) NOT NULL," +
                "coach_name VARCHAR(50) NOT NULL);");
        statement.execute("DROP TABLE player");
        statement.execute("CREATE TABLE IF NOT EXISTS player (" +
                "player_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "team_id INT NOT NULL," +
                "first_name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NUL,L" +
                "jersey_number INT NOT NULL," +
                "FOREIGN KEY (team_id) REFERENCES team(team_id));");
        statement.execute("DROP TABLE game");
        statement.execute("CREATE TABLE IF NOT EXISTS game (" +
                "game_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "team1_id INT NOT NULL," +
                "team2_id INT NOT NULL," +
                "team1_score INT NOT NULL," +
                "team2_score INT NOT NULL," +
                "FOREIGN KEY (team1_id) REFERENCES team(team_id)," +
                "FOREIGN KEY (team2_id) REFERENCES team(team_id));");
        connection.close();
    }

    public static void main(String[] args)
    {
        String menu = "Team Roster Manager\n" +
                "\n" +
                "1. Add Team\n" +
                "2. Add Player\n" +
                "3. Add Game Result\n" +
                "4. Edit Player Jersey Number\n" +
                "5. Remove Player\n" +
                "6. Display Teams\n" +
                "7. Display Players\n" +
                "8. Display Games\n" +
                "9.Print Team Report\n" +
                "10. Exit";
        Scanner scan = new Scanner(System.in);
        int resM = 0;

        //safe mode
        System.out.println(menu + "\n");
        resM = scan.nextInt();

//        boolean tf = true;
//
//            try
//            {
//                System.out.println(menu + "\n");
//                do {
//                    resM = scan.nextInt();
//                    scan.nextLine();
//                } while (scan.nextLine().);
//
//            } catch (Exception e) {
//                System.out.println("Enter numbers only");
//            }
        System.out.println(resM);
    }
}
