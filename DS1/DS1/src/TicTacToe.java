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
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
//            printWriter.print(" , , \n , , \n , , ");


            // scnning file
            Scanner fileSc = new Scanner(file).useDelimiter("\n");
            for (int i = 0; i < 0; i++) { // needs fixing
                String line = fileSc.nextLine();
                System.out.println(line);
                // scanner for each specific line
                Scanner lineSc = new Scanner(line).useDelimiter("[ ,]");
                String[] arr = new String[3];
                for (int j = 0; j < 3; j++) {
//                    if ()
                    arr[i] = lineSc.next();
                    System.out.println(arr[i]);
                }
                board[i] = arr;
            }
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
                        {
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    if (board[i][j].equals(" "))
                                        board[i][j] = "3";
                                    if (board[i][j].equals("X"))
                                        board[i][j] = "0";
                                    if (board[i][j].equals("O"))
                                        board[i][j] = "1";
                                }
                            }
                            // prints game to txt file
                            String game = board[0][0] + " " + board[0][1] + " " + board[0][2] + "\n" +
                                    board[1][0] + " " + board[1][1] + " " + board[1][2] + "\n" +
                                    board[2][0] + " " + board[2][1] + " " + board[2][2] + "";
                            printWriter.println(game); // doesn't work
//                            printWriter.print(board[0][0] + " " + board[0][1] + " " + board[0][2] + "\n" +
//                                              board[1][0] + " " + board[1][1] + " " + board[1][2] + "\n" +
//                                              board[2][0] + " " + board[2][1] + " " + board[2][2] + "");
                            System.out.println(game);
                            System.out.println("Save complete.\nGood bye.");
                            printWriter.close();
                            System.exit(0);
                        }
                        if (row >= 0 && row < 3 && col >= 0 && col < 3 && Objects.equals(board[row][col], " ")) {
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
                        printWriter.print(" , , \n , , \n , , ");
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
                        break;
                    }
                    if (player.equals("X"))
                        player = "O";
                    else //if (player.equals("O"))
                        player = "X";
                }
                printWriter.close();
            }
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
