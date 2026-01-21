public class DS8_BFS
{
    public static int breadthFirstSearch_Portals (char[][]maze)
    {
        int coll = maze.length;
        boolean[][] bt = new boolean[maze.length][maze[0].length];
        DS8_Queue<Integer> queue = new DS8_Queue<>();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S')
                {
                    bt[i][j] = true;
                    queue.add(i*coll + j);
                }
            }
        }


        int count = 0;
        while (!queue.isEmpty())
        {
            int t = queue.poll();
            int r = t/coll;
            int c = t% coll;

            if (maze[r][c] == 'E')
                return count;

            //up
            if (r-1 >= 0 && !bt[r-1][c] && maze[r-1][c] != 'W')
            {
                bt[r-1][c] = true;
            }
            //down
            if (r+1 < maze.length && !bt[r+1][c] && maze[r+1][c] != 'W')
            {
                bt[r+1][c] = true;
            }
            //left
            if (c-1 >= 0 && !bt[r][c-1] && maze[r][c-1] != 'W')
            {
                bt[r][c-1] = true;
            }
            //uriht
            if (c+1 < maze[0].length && !bt[r][c] && maze[r][c+1] != 'W')
            {
                bt[r-1][c] = true;
            }

            count++;
        }
        return -1;
    }
}
