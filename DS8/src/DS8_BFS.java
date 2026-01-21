import java.awt.Point;

public class DS8_BFS
{
    public static int breadthFirstSearch_Portals (char[][]maze)
    {
        boolean[][] bt = new boolean[maze.length][maze[0].length];
//        int[][] v = new int[maze.length][maze[0].length];
//        for (int i = 0; i < v.length; i++) {
//            for (int j = 0; j < v[i].length; j++) {
//                v[i][j] = -1;
//            }
//        }
        DS8_Queue<Point[]> queue = new DS8_Queue<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S' || maze[i][j] == 's') {
                    queue.add(new Point[]{new Point(i,j)});
                    bt[i][j] = true;
                }
            }
        }
        int count = 0;
        while (!queue.isEmpty())
        {
            Point[] s = queue.poll();
            int r = s[s.length-1].x;
            int c = s[s.length-1].y;
            bt[r][c] = true;

            if (maze[r][c] == 'E')
                return queue.size();
            count++;

            char start;
            if ("aAbBcCdD".contains(maze[r][c]+"")) {
                start = maze[r][c];
                if (Character.isUpperCase(start)) {
                    for (int i = 0; i < maze.length; i++) {
                        for (int j = 0; j < maze[0].length; j++) {
                            if (maze[i][j] == Character.toLowerCase(start) && !bt[i][j]) {
                                bt[i][j] = true;
                                queue.add(new Point[]{new Point(i,j)});
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < maze.length; i++) {
                        for (int j = 0; j < maze[0].length; j++) {
                            if (maze[i][j] == Character.toUpperCase(start) && !bt[i][j]) {
                                bt[i][j] = true;
                                queue.add(new Point[]{new Point(i,j)});
                            }
                        }
                    }
                }
            }

            //up
            if (r-1 >= 0 && !bt[r-1][c] && maze[r-1][c] != 'W')
            {
                bt[r-1][c] = true;
                queue.add(new Point[]{new Point(r-1,c)});
            }
            //down
            if (r+1 < maze.length && !bt[r+1][c] && maze[r+1][c] != 'W')
            {
                bt[r+1][c] = true;
                queue.add(new Point[]{new Point(r+1,c)});
            }
            //left
            if (c-1 >= 0 && !bt[r][c-1] && maze[r][c-1] != 'W')
            {
                bt[r][c-1] = true;
                queue.add(new Point[]{new Point(r,c-1)});

            }
            //right
            if (c+1 < maze[0].length && !bt[r][c+1] && maze[r][c+1] != 'W')
            {
                bt[r][c+1] = true;
                queue.add(new Point[]{new Point(r,c+1)});
            }
        }
        return -1;
    }
}
