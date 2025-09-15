import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class TicTacToe
{
    public static void main(String[] args)
    {

        Scanner scan = new Scanner(System.in);
        String[][] board = {{" "," "," "},
                {" "," "," "},
                {" "," "," "}};
        try {
            File file = new File("TicTacToe.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileSc = new Scanner(file).useDelimiter("\n");

            int r =0;
            while (fileSc.hasNextLine())
            {
                int c = 0;
                String line = fileSc.nextLine();
                Scanner ls = new Scanner(line).useDelimiter(" ");

                while (ls.hasNextInt()) // either it's not copying properly or line 63
                {
                    board[r][c] = String.valueOf(ls.nextInt());
                    c++;
                }
                r++;
                ls.close();
            }

            FileWriter fileWriter = new FileWriter(file,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
//            printWriter.print(" , , \n , , \n , , ");


            // scanning file

//            int r =0;
//            while (fileSc.hasNextLine())
//            {
//                int c = 0;
//                String line = fileSc.nextLine();
//                Scanner ls = new Scanner(line).useDelimiter(" ");
//
//                while (ls.hasNextInt())
//                {
//                    board[r][c] = String.valueOf(ls.nextInt());
//                    System.out.println(String.valueOf(ls.nextInt()));
//                    c++;
//                }
//                r++;
//            }
            boolean gameSave = false;

            for (int i = 0; i < board.length; i++) { // needs fixing
                for (int j = 0; j < 3; j++) { // probably not changing properly
                    if (board[i][j].equals("0"))
                    {
                        board[i][j] = "X";
                        gameSave = true;
                    }
                    else if (board[i][j].equals("1"))
                    {
                        board[i][j] = "O";
                        gameSave = true;
                    }
                    else if (board[i][j].equals("3"))
                    {
                        board[i][j] = " ";
                    }
                }
            }
            if (gameSave)
                System.out.println("Game save loaded.");

            String player = "X";
            while (true) {
                // computer's move
                if (player.equals("O"))
                {
                    int r1 = (int) (Math.random() * 3);
                    int r2 = (int) (Math.random() * 3);

                    while (!Objects.equals(board[r1][r2], " ")) {
                        r1 = (int) (Math.random() * 3);
                        r2 = (int) (Math.random() * 3);
                    }
                    board[r1][r2] = "O";
                }
                else if (player.equals("X")) {

                    // prin board
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length - 1; j++) {
                            System.out.print(" " + board[i][j] + " |");
                        }
                        System.out.print(" " + board[i][board.length - 1] + " ");
                        if (i != 2) {
                            System.out.println("\n-----------");
                        }
                    }
                    System.out.println();

                    int row;
                    int col;
                    while (true) {
                        // asks for position
                        System.out.println("\nEntering 3 for the column will save the game");
                        System.out.println("Enter a column from 0 to 2:");
                        col = scan.nextInt();
                        System.out.println("Enter a row from 0 to 2:");
                        row = scan.nextInt();
                        if (col == 3)
                        { // i think the error is here - copying wrong after 2 saves
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    if (board[i][j].equals(" "))
                                        board[i][j] = "3";
                                    else if (board[i][j].equals("X"))
                                        board[i][j] = "0";
                                    else if (board[i][j].equals("O"))
                                        board[i][j] = "1";
                                }
                            }
                            // prints game to txt file
                            String game = board[0][0] + " " + board[0][1] + " " + board[0][2] + "\n" +
                                          board[1][0] + " " + board[1][1] + " " + board[1][2] + "\n" +
                                          board[2][0] + " " + board[2][1] + " " + board[2][2] + "";

                            printWriter.println(game);
//                            printWriter.print(board[0][0] + " " + board[0][1] + " " + board[0][2] + "\n" +
//                                              board[1][0] + " " + board[1][1] + " " + board[1][2] + "\n" +
//                                              board[2][0] + " " + board[2][1] + " " + board[2][2] + "");
//                            System.out.println(game);
                            System.out.println("Save complete.\nGood bye.");
                            printWriter.close();
                            System.exit(0);
                        }
                        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col].equals(" "))
                        {
                            board[row][col] = "X";
                            break;
                        } else {
                            System.out.println("Invalid move, enter a new move.");
                        }
                    }
                }


                //checks for winner draw and switches te player
                {
                    if (isWinner(board, player)) {
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length - 1; j++) {
                                System.out.print(" " + board[i][j] + " |");
                            }
                            System.out.print(" " + board[i][board.length - 1] + " ");
                            if (i != 2) {
                                System.out.println("\n-----------");
                            }
                        }
                        System.out.println("\n\n" + player + " WINS!");
                        scan.close();
                        fileSc.close();
                        fileWriter.close();
                        printWriter.close();
                        file.delete();
                        break;
                    }
                    if (isDraw(board)) {
                        for (int i = 0; i < board.length; i++) {
                            for (int j = 0; j < board[i].length - 1; j++) {
                                System.out.print(" " + board[i][j] + " |");
                            }
                            System.out.print(" " + board[i][board.length - 1] + " ");
                            if (i != 2)
                                System.out.println("\n-----------");
                        }
                        System.out.println("\n\nDraw game.");
                        scan.close();
                        fileSc.close();
                        fileWriter.close();
                        printWriter.close();
                        file.delete();
                        break;
                    }
                    if (player.equals("X"))
                        player = "O";
                    else //if (player.equals("O"))
                        player = "X";
                }
            }
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isWinner(String[][] board, String player)
    {

        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player) ||
                    board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player))
                return true;
        }
        return board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) ||
                board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player);
    }
    public static boolean isDraw(String[][] board)
    {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (Objects.equals(board[i][j], " ") || isWinner(board, "X") || isWinner(board, "O"))
                    return false;

        return true;
    }
}