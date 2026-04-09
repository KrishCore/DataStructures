package WumpusWorld;

import java.util.LinkedList;
import java.util.Queue;

public class WumpusMap
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid = new WumpusSquare[NUM_ROWS][NUM_COLUMNS];;
    private int ladderR;
    private int ladderC;

    public WumpusMap()
    {
        createMap();
    }

    public void createMap()
    {
        boolean solvable = false;
        while (!solvable)
        {
            //initialize
            {
                for (int i = 0; i < NUM_ROWS; i++)
                    for (int j = 0; j < NUM_COLUMNS; j++)
                        grid[i][j] = new WumpusSquare();
            }

            //ladder
            {
                ladderR = (int) (Math.random() * NUM_ROWS);
                ladderC = (int) (Math.random() * NUM_COLUMNS);
                grid[ladderR][ladderC].setLadder(true);
            }

            //pits
            {
                int pitsPlaced = 0;
                while (pitsPlaced < NUM_PITS) {
                    int r = (int) (Math.random() * NUM_ROWS);
                    int c = (int) (Math.random() * NUM_COLUMNS);
                    if (!grid[r][c].isPit() && !grid[r][c].isLadder()) {
                        grid[r][c].setPit(true);
                        pitsPlaced++;
                        addBreeze(r, c);
                    }
                }
            }

            //gold
            {
                int gr, gc;
                while (true) {
                    gr = (int) (Math.random() * NUM_ROWS);
                    gc = (int) (Math.random() * NUM_COLUMNS);
                    if (!grid[gr][gc].isLadder() && !grid[gr][gc].isPit()) {
                        grid[gr][gc].setGold(true);
                        break;
                    }
                }

//        bonus - place it in a solvable place
                //solvable check
                if (hasPath(ladderR, ladderC, gr, gc))
                    solvable = true;
                else System.out.println("map changed");
            }

            //wumpus
            {
                while (true) {
                    int r = (int) (Math.random() * NUM_ROWS);
                    int c = (int) (Math.random() * NUM_COLUMNS);
                    if (!grid[r][c].isPit() && !grid[r][c].isLadder()) {
                        grid[r][c].setWumpus(true);
                        addStench(r, c);
                        break;
                    }
                }
            }
        }
    }
    public boolean hasPath(int x1, int y1, int x2, int y2) {
        //validity check (boundaries)
        if (x1 < 0 || x1 >= NUM_ROWS || y1 < 0 || y1 >= NUM_COLUMNS ||
                x2 < 0 || x2 >= NUM_ROWS || y2 < 0 || y2 >= NUM_COLUMNS) return false;

        //start or end on a pit
        if (isHazard(x1, y1) || isHazard(x2, y2)) return false;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[NUM_ROWS][NUM_COLUMNS];

        queue.add(new int[]{x1, y1});
        visited[x1][y1] = true;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currX = current[0];
            int currY = current[1];

            if (currX == x2 && currY == y2) return true;

            for (int[] dir : directions) {
                int newX = currX + dir[0];
                int newY = currY + dir[1];

                //check boundaries of new points, check if hazard and if visited
                if (newX >= 0 && newX < NUM_ROWS && newY >= 0 && newY < NUM_COLUMNS && !isHazard(newX, newY) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        return false;
    }

    //helper method or above method
    private boolean isHazard(int r, int c)
    {
        return grid[r][c].isPit();
    }

    public void addBreeze(int r, int c)
    {
        if (r > 0)
            grid[r-1][c].setBreeze(true);
        if (r+1 <= NUM_ROWS -1)
            grid[r+1][c].setBreeze(true);
        if (c > 0)
            grid[r][c-1].setBreeze(true);
        if (c+1 <= NUM_COLUMNS-1)
            grid[r][c+1].setBreeze(true);
    }

    public void addStench(int r, int c)
    {
        if (r > 0)
            grid[r-1][c].setStench(true);
        if (r+1 <= NUM_ROWS -1)
            grid[r+1][c].setStench(true);
        if (c > 0)
            grid[r][c-1].setStench(true);
        if (c+1 <= NUM_COLUMNS-1)
            grid[r][c+1].setStench(true);
    }

    public int getLadderC() {
        return ladderC;
    }

    public int getLadderR() {
        return ladderR;
    }

    public WumpusSquare getWumpusSquare(int row, int col) {
        return (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLUMNS) ? null : grid[row][col];
    }

    public String toString()
    {
        //lpgdw*
        String s = "";
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++)
                s += grid[i][j].toString();
            s += "\n";
        }
        return s;
    }
}
