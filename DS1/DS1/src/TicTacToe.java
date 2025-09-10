import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TicTacToe
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        char[][] board = {{' ',' ',' '},
                          {' ',' ',' '},
                          {' ',' ',' '}};
        try {
            File fileRef = new File("TicTacToe");
            if (!fileRef.exists())
                fileRef.createNewFile();
            FileWriter fw = new FileWriter(fileRef);
            PrintWriter pw = new PrintWriter(fw);
            char player = 'X';
            while (true) {
                if (player == 'O')
                {
                    int r1 = (int) (Math.random() * 3);
                    int r2 = (int) (Math.random() * 3);

                    while (board[r1][r2] != ' ') {
                        r1 = (int) (Math.random() * 3);
                        r2 = (int) (Math.random() * 3);
                    }
                    board[r1][r2] = 'O';
                }
                else if (player == 'X') {

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
                        System.out.println("\nEntering 3 for the column will save the game");
                        System.out.println("Enter a column from 0 to 2:");
                        col = scan.nextInt();
                        System.out.println("Enter a row from 0 to 2:");
                        row = scan.nextInt();
                        if (col == 3)
                        {
                            pw.println(board);
                            System.out.println("Save complete.\nGood bye.");
                            System.exit(0);
                        }
                        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                            board[row][col] = 'X';
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
                    if (player == 'X')
                        player = 'O';
                    else //if (player == 'O')
                        player = 'X';
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isWinner(char[][] board, char player)
    {

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player ||
                    board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        return board[0][0] == player && board[1][1] == player && board[2][2] == player ||
                board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }
    public static boolean isDraw(char[][] board)
    {
        boolean isCat = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ' || isWinner(board, 'X') || isWinner(board, 'O')) {
                    return false;
                }
            }
        }
        return isCat;
    }
}
