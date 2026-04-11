package Puzzle;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleBoard
{
    private int[][] board = new int[4][4];
    private int zeroR, zeroC = 0;

    public PuzzleBoard()
    {
        shuffle();
    }

    public void shuffle()
    {
        do {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < 16; i++)
                list.add(i);
            Collections.shuffle(list);

            int index = 0;
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++) {
                    board[r][c] = list.get(index++);
                    if (board[r][c] == 0)
                    {
                        zeroR = r;
                        zeroC = c;
                    }
                }
        } while (!isSolvable());
    }

    public boolean isSolvable()
    {
        int[] arr = new int[16];
        int index = 0;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                arr[index++] = board[i][j];

        int inversions = 0;

        for (int i = 0; i < 16; i++)
            for (int j = i+1; j < 16; j++)
                if (arr[i] != 0 && arr[j] != 0 && arr[i] > arr[j])
                    inversions++;

        int rowOfZero = -1;
        for (int r = 0; r < 4; r++) {
            boolean found = false;
            for (int c = 0; c < 4; c++) {
                if (board[r][c] == 0) {
                    rowOfZero = r;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        int rowFromBottom = 4 - rowOfZero;

        if (rowFromBottom % 2 == 0)
            return inversions % 2 == 1;
        else return inversions % 2 == 0;
    }

    public boolean move(int r, int c)
    {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (board[i][j] == 0)
                {
                    zeroR = i;
                    zeroC = j;
                }

        if ((Math.abs(r - zeroR) == 1 && c == zeroC) || (Math.abs(c - zeroC) == 1 && r == zeroR))
        {
            int temp = board[r][c];
            board[r][c] = 0;
            board[zeroR][zeroC] = temp;
            zeroR = r;
            zeroC = c;
            return true;
        }
        return false;
    }

    public boolean isSolved()
    {
        int count = 1;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                //checks the last number at the end
                if (r == 3 && c == 3 && board[r][c] == 0)
                    return true;
                //noraml check
                if (board[r][c] != count++)
                    return false;
            }
        }
        return false;
    }

    public int getValue(int r, int c)
    {
        return board[r][c];
    }
}
