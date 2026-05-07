import java.sql.*;
import java.util.Scanner;

public class TeamManager
{
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password");
        } catch (Exception e) {
            System.out.println(e);
        }
        Statement statement = connection.createStatement();

        statement.execute("CREATE DATABASE IF NOT EXISTS team_manager_3");
        statement.execute("USE team_manager_3");
        statement.execute("DROP TABLE IF EXISTS game");
        statement.execute("DROP TABLE IF EXISTS player");
        statement.execute("DROP TABLE IF EXISTS team");
        statement.execute("CREATE TABLE IF NOT EXISTS team (" +
                "team_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "team_name VARCHAR(50) NOT NULL," +
                "coach_name VARCHAR(50) NOT NULL);");
        statement.execute("CREATE TABLE IF NOT EXISTS player (" +
                "player_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "team_id INT NOT NULL," +
                "first_name VARCHAR(50) NOT NULL," +
                "last_name VARCHAR(50) NOT NULL," +
                "jersey_number INT NOT NULL," +
                "FOREIGN KEY (team_id) REFERENCES team(team_id));");
        statement.execute("CREATE TABLE IF NOT EXISTS game (" +
                "game_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "team1_id INT NOT NULL," +
                "team2_id INT NOT NULL," +
                "team1_score INT NOT NULL," +
                "team2_score INT NOT NULL," +
                "FOREIGN KEY (team1_id) REFERENCES team(team_id)," +
                "FOREIGN KEY (team2_id) REFERENCES team(team_id));");


        String menu = "Team Roster Manager:\n" +
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
        int resM = -1;
        while (resM != 10) {
            Scanner scan = new Scanner(System.in);

            //easy method
            System.out.println(menu);
            resM = scan.nextInt();
            while (resM < 1 || resM > 10)
            {
                System.out.println("Enter a number between 1 and 10\n" + menu);
                resM = scan.nextInt();
            }

            //safe mode
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
            if (resM == 1) {
                scan.nextLine();
                System.out.println("Enter Team Name:");
                String name = scan.nextLine();
                System.out.println("Enter Team Coach:");
                String coach = scan.nextLine();
                statement.executeUpdate("INSERT INTO team (team_name, coach_name) VALUES ('" + name + "', '" + coach + "');");
            }
            if (resM == 2) {
                scan.nextLine();
                System.out.println("Enter First Name:");
                String first = scan.nextLine();
                System.out.println("Enter Last Name:");
                String last = scan.nextLine();
                System.out.println("Enter Jersey Number:");
                int num = scan.nextInt();
                statement.executeUpdate("INSERT INTO team (team_name, last_name, jersey_number) VALUES ('" + first + "', '" + last + "', " + num + ");");
            }
            if (resM == 3) { // work from here
                scan.nextLine();
                System.out.println("Enter Team Name:");
                String name = scan.nextLine();
                System.out.println("Enter Team Coach:");
                String coach = scan.nextLine();
                statement.executeUpdate("INSERT INTO team (team_name, coach_name) VALUES ('" + name + "', '" + coach + "');");
            }

        }
        connection.close();
    }
}
