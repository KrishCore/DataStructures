package WumpusWorld;

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
        //initialize
        {
            for (int i = 0; i < NUM_ROWS; i++)
                for (int j = 0; j < NUM_COLUMNS; j++)
                    grid[i][j] = new WumpusSquare();
        }

        //ladder
        {
            ladderR = (int) (Math.random()* NUM_ROWS);
            ladderC = (int) (Math.random()*NUM_COLUMNS);
            grid[ladderR][ladderC].setLadder(true);
        }

        //pits
        {
            int pitsPlaced = 0;
            while (pitsPlaced < NUM_PITS)
            {
                int r = (int) (Math.random()*NUM_ROWS);
                int c = (int) (Math.random()*NUM_COLUMNS);
                if (!grid[r][c].isPit() && !grid[r][c].isLadder()) {
                    grid[r][c].setPit(true);
                    pitsPlaced++;
                    addBreeze(r, c);
                }
            }
        }

        //gold
        {
            while (true)
            {
                int r = (int) (Math.random()* NUM_ROWS);
                int c = (int) (Math.random()*NUM_COLUMNS);
                if (!grid[r][c].isLadder() && !grid[r][c].isPit()) {
                    grid[r][c].setGold(true);
                    break;
                }
            }

            //solvable check
            //private ArrayList<String> getStrings(WumpusSquare sq) {
            //        ArrayList<String> messages = new ArrayList<>();
            //        //messages
            //        {
            //            if (status != WON && sq.isLadder())
            //                messages.add("You bump into a ladder");
            //            if (status != WON && wumpusKilled) {
            //                messages.add("You hear a scream.");
            //                wumpusKilled = false;
            //            }
            //            if (status == PLAYING && sq.isBreeze())
            //                messages.add("You feel a breeze.");
            //            if (status == PLAYING && sq.isStench() || sq.isDeadWumpus())
            //                messages.add("You smell a stench.");
            //            if (status == PLAYING && sq.isGold() && !player.hasGold())
            //                messages.add("You see a glimmer.");
            //
            //            if (status == WON)
            //                messages.add("You Win. (N for new game)");
            //            if (sq.isPit())
            //                messages.add("You fell down a pit to your death.");
            //            if (sq.isWumpus())
            //                messages.add("You are eaten by the Wumpus.");
            //            if (status == DEAD)
            //                messages.add("Game Over. (N for new game)");
            //        }
            //        return messages;
            //    }
//        bonus - place it in a solvable place
        }

        //wumpus
        {
            while (true)
            {
                int r = (int) (Math.random() * NUM_ROWS);
                int c = (int) (Math.random() * NUM_COLUMNS);
                if (!grid[r][c].isPit() && !grid[r][c].isGold() && !grid[r][c].isLadder()) {
                    grid[r][c].setWumpus(true);
                    addStench(r, c);
                    break;
                }
            }
        }
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
