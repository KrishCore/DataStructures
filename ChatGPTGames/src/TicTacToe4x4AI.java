import java.util.Scanner;

public class TicTacToe4x4AI {

    private static final int SIZE = 4;
    private static final char EMPTY = ' ';
    private static final char HUMAN = 'X';
    private static final char AI = 'O';

    private char[][] board = new char[SIZE][SIZE];
    private Scanner scanner = new Scanner(System.in);

    public TicTacToe4x4AI() {
        // Initialize board
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = EMPTY;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(" ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("---+---+---+---");
        }
        System.out.println();
    }

    public void play() {
        System.out.println("Welcome to 4x4 Tic Tac Toe!");
        printBoard();

        while (true) {
            humanMove();
            printBoard();
            if (checkWin(HUMAN)) {
                System.out.println("You win!");
                break;
            }
            if (isFull()) {
                System.out.println("It's a draw!");
                break;
            }

            System.out.println("Computer's move:");
            computerMove();
            printBoard();
            if (checkWin(AI)) {
                System.out.println("Computer wins!");
                break;
            }
            if (isFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private void humanMove() {
        while (true) {
            System.out.print("Enter your move (row and column: 1-4 1-4): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (isValid(row, col)) {
                board[row][col] = HUMAN;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void computerMove() {
        int[] move = findBestMove();
        board[move[0]][move[1]] = AI;
    }

    private int[] findBestMove() {
        // 1. Win if possible
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == EMPTY) {
                    board[i][j] = AI;
                    if (checkWin(AI)) return new int[]{i, j};
                    board[i][j] = EMPTY;
                }

        // 2. Block human win
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == EMPTY) {
                    board[i][j] = HUMAN;
                    if (checkWin(HUMAN)) {
                        board[i][j] = EMPTY;
                        return new int[]{i, j};
                    }
                    board[i][j] = EMPTY;
                }

        // 3. Pick center if available
        if (board[1][1] == EMPTY) return new int[]{1, 1};
        if (board[2][2] == EMPTY) return new int[]{2, 2};

        // 4. Otherwise, pick first empty
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == EMPTY)
                    return new int[]{i, j};

        // Should not happen
        return new int[]{0, 0};
    }

    private boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(player, board[i][0], board[i][1], board[i][2], board[i][3])) return true;
            if (checkLine(player, board[0][i], board[1][i], board[2][i], board[3][i])) return true;
        }
        // Check diagonals
        if (checkLine(player, board[0][0], board[1][1], board[2][2], board[3][3])) return true;
        if (checkLine(player, board[0][3], board[1][2], board[2][1], board[3][0])) return true;

        return false;
    }

    private boolean checkLine(char player, char c1, char c2, char c3, char c4) {
        return (c1 == player && c2 == player && c3 == player && c4 == player);
    }

    private boolean isFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == EMPTY) return false;
        return true;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    public static void main(String[] args) {
        new TicTacToe4x4AI().play();
    }
}
