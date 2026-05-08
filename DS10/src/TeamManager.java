import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;
import java.util.Scanner;

public class TeamManager
{
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "SQLPa55w0rd");
        } catch (Exception e) {
            System.out.println(e);
        }
        Statement statement = connection.createStatement();

        statement.execute("CREATE DATABASE IF NOT EXISTS team_manager_3");
        statement.execute("USE team_manager_3");
//        statement.execute("DROP TABLE IF EXISTS game");
//        statement.execute("DROP TABLE IF EXISTS player");
//        statement.execute("DROP TABLE IF EXISTS team");
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
                "9. Print Team Report\n" +
                "10. Exit";
        int resM = -1;
        while (resM != 10) {
            Scanner scan = new Scanner(System.in);

            //easy method
            System.out.println(menu);
            System.out.print("\nEnter choice: ");
            resM = scan.nextInt();
            while (resM < 1 || resM > 10)
            {
                System.out.println("\nEnter a number between 1 and 10");
                System.out.print("Enter choice: ");
                resM = scan.nextInt();
            }

            //safe mode
            System.out.print("");
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
//            System.out.println(resM);
            if (resM == 1) // add team
            {
                scan.nextLine();
                System.out.print("Enter team name: ");
                String name = scan.nextLine();
                if (name.equals("-1")) break;
                System.out.print("Enter team coach: ");
                String coach = scan.nextLine();
                if (coach.equals("-1")) break;
                statement.executeUpdate("INSERT INTO team (team_name, coach_name) VALUES ('" + name + "', '" + coach + "');");
                System.out.println("\nTeam added.\n");
            }
            if (resM == 2) // add player
            {
                scan.nextLine();
                System.out.print("Enter team ID: ");
                int id = scan.nextInt();
                if (id == -1) break;
                System.out.print("Enter first name: ");
                scan.nextLine();
                String first = scan.nextLine();
                if    (first.equals("-1")) break;
                System.out.print("Enter last name: ");
                String last = scan.nextLine();
                if    (last.equals("-1")) break;
                System.out.print("Enter jersey number: ");
                int jNum = scan.nextInt();
                if (jNum == -1) break;
                try {
                    statement.executeUpdate("INSERT INTO player (team_id, first_name, last_name, jersey_number) VALUES ('" + id + "', '" + first + "', '" + last + "', '" + jNum + "');");
                    System.out.println("\nPlayer added.\n");
                } catch (SQLException e)
                {
                    System.out.println("Team id " + id + " not found.\n");
                }
            }
            if (resM == 3) // add game
            {
                scan.nextLine();
                System.out.print("Enter team 1 ID: ");
                int t1 = scan.nextInt();
                if (t1 == -1) break;
                System.out.print("Enter team 2 ID: ");
                int t2 = scan.nextInt();
                if (t2 == -1) break;
                System.out.print("Enter team 1 score: ");
                int s1 = scan.nextInt();
                if (s1 == -1) break;
                System.out.print("Enter team 2 score: ");
                int s2 = scan.nextInt();
                if (s2 == -1) break;
                try {
                    statement.executeUpdate("INSERT INTO game (team1_id, team2_id, team1_score, team2_score) VALUES ('" + t1 + "', '" + t2 + "', '" + s1 + "', '" + s2 + "');");
                    System.out.println("\nGame added.\n");
                } catch (SQLException e) {
                    System.out.println("Enter valid team ids.\n");
                }
            }
            if (resM == 4) // edit player jersey number
            {
                scan.nextLine();
                System.out.print("Enter player ID: ");
                int id = scan.nextInt();
                if (id == -1) break;
                System.out.print("Enter new jersey number: ");
                int jNum = scan.nextInt();
                if (jNum == -1) break;
                try {
                    statement.executeUpdate("UPDATE player SET jersey_number = " + jNum + " WHERE player_id = " + id + ";");
                    System.out.println("\nPlayer updated.\n");
                } catch (SQLException e) {
                    System.out.println("Player id " + id + " not found.\n");
                }
            }
            if (resM == 5) // remove player
            {
                scan.nextLine();
                System.out.print("Enter player ID: ");
                int id = scan.nextInt();
                if (id == -1) break;
                try {
                    statement.executeUpdate("DELETE FROM player WHERE player_id = " + id + ";");
                    System.out.println("Player removed.");
                } catch (SQLException e) {
                    System.out.println("Player id " + id + " not found.\n");
                }
            }
            if (resM == 6) // display teams
            {
                try {
                    ResultSet rs = statement.executeQuery("SELECT * FROM team;");
                    System.out.printf("%-4s %-11s %s\n", "ID", "Team Name", "Coach");
                    while (rs.next())
                        System.out.printf("%-4s %-11s %s\n", rs.getInt("team_id"), rs.getString("team_name"), rs.getString("coach_name"));
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (resM == 7) // display players
            {
                try {
                    ResultSet rs = statement.executeQuery("SELECT player.player_id, player.first_name, player.last_name, player.jersey_number, team.team_name FROM player JOIN team ON player.team_id = team.team_id;");
                    System.out.printf("%-4s %-13s %-8s %-3s\n", "ID", "Player Name", "Jersey", "Team");
                    while (rs.next())
                        System.out.printf("%-4s %-13s %-8s %-3s\n", rs.getInt("player_id"), rs.getString("first_name") + " " + rs.getString("last_name"), rs.getInt("jersey_number"), rs.getString("team_name"));
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // work from 8
            if (resM == 8) // display games
            {
                //
            }
            if (resM == 9) // print team report
            {
                scan.nextLine();
                System.out.print("Enter team ID: ");
                int id = scan.nextInt();
                try {
                    Statement stmt1 = connection.createStatement();

                    ResultSet tr = stmt1.executeQuery("SELECT team.team_name, team.coach_name FROM team  JOIN player ON team.team_id = player.team_id WHERE team.team_id = " + id);
                    tr.next();
                    System.out.println("\nTeam Report: " + tr.getString("team_name") + "\nCoach: " + tr.getString("coach_name"));

                    Statement stmt2 = connection.createStatement();
                    ResultSet pl = stmt2.executeQuery( "SELECT jersey_number, first_name, last_name FROM player WHERE team_id = " + id);
                    System.out.println("\nPlayers:");
                    while (pl.next())
                        System.out.printf("%-4s %-13s \n", pl.getInt("jersey_number"), pl.getString("first_name") + " " + pl.getString("last_name"));

                    Statement stmt3 = connection.createStatement();
                    ResultSet gr = stmt3.executeQuery( "SELECT * FROM game WHERE team1_id = " + id + " OR team2_id = " + id);

                    System.out.println("\nGame Results:");
                    int wins = 0, losses = 0, gameCount = 0, totalPoints = 0;
                    while (gr.next())
                    {
                        int t1id = gr.getInt("team1_id");
                        int t2id = gr.getInt("team2_id");
                        int s1 = gr.getInt("team1_score");
                        int s2 = gr.getInt("team1_score");

                        int opponentId, ms, os;

                        if (t1id == id) //which team are we
                        {
                            opponentId = t2id;
                            ms = s1;
                            os = s2;
                        }
                        else {
                            opponentId = t1id;
                            ms = s2;
                            os = s1;
                        }

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT team_name FROM team WHERE team_id = " + opponentId); //find opp team name
                        rs.next();
                        String opName = rs.getString("team_name");
                        //work from here

                        char wl;
                        if (ms > os) {
                            wl = 'W';
                            wins++;
                        }
                        else if (ms < os) {
                            wl = 'L';
                            losses++;
                        }
                        else wl = 'T';
                        System.out.printf("vs %s %-11s %-14s", gr.getString("team"), wl, gr.getString("team1_score" + "-" + gr.getString("team2_score")));
                    }

                    System.out.println("\nRecord: " + wins + "-" + losses);
                    double avg = (double) totalPoints /gameCount;
                    System.out.println("Average Points Scored: " + avg);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resM == 10) // exit
            {
                System.exit(0);
                connection.close();
            }

        }
    }
}
