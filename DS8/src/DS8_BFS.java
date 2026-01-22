import java.awt.Point;

public class DS8_BFS
{
    public static int breadthFirstSearch_Portals (char[][]maze)
    {
        boolean[][] bt = new boolean[maze.length][maze[0].length];
        DS8_Queue<Point[]> queue = new DS8_Queue<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    queue.add(new Point[]{new Point(i,j)});
                    bt[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty())
        {
            Point[] s = queue.poll();
            int r = s[s.length-1].x;
            int c = s[s.length-1].y;
            bt[r][c] = true;

            if (maze[r][c] == 'E')
                return s.length-1;

            char start;
            if ("aAbBcCdD".contains(maze[r][c]+"")) {
                start = maze[r][c];
                if (Character.isUpperCase(start))
                    for (int i = 0; i < maze.length; i++) {
                        for (int j = 0; j < maze[0].length; j++) {
                            if (!bt[i][j] && (maze[i][j] == Character.toLowerCase(start)
                                    || maze[i][j] == Character.toUpperCase(start))) {
                                bt[i][j] = true;
                                Point[] np = new Point[s.length+1];
                                for (int k = 0; k < np.length-1; k++) {
                                    np[k] = s[k];
                                }
                                np[np.length-1] = new Point(i,j);
                                queue.add(np);
                            }
                        }
                    }
            }
            //up
            if (r-1 >= 0 && !bt[r-1][c] && maze[r-1][c] != 'W')
            {
                bt[r-1][c] = true;
                Point[] np = new Point[s.length+1];
                for (int k = 0; k < np.length-1; k++) {
                    np[k] = s[k];
                }
                np[np.length-1] = new Point(r-1,c);
                queue.add(np);
            }
            //down
            if (r+1 < maze.length && !bt[r+1][c] && maze[r+1][c] != 'W')
            {
                bt[r+1][c] = true;
                Point[] np = new Point[s.length+1];
                for (int k = 0; k < np.length-1; k++) {
                    np[k] = s[k];
                }
                np[np.length-1] = new Point(r+1,c);
                queue.add(np);
            }
            //left
            if (c-1 >= 0 && !bt[r][c-1] && maze[r][c-1] != 'W')
            {
                bt[r][c-1] = true;
                Point[] np = new Point[s.length+1];
                for (int k = 0; k < np.length-1; k++) {
                    np[k] = s[k];
                }
                np[np.length-1] = new Point(r,c-1);
                queue.add(np);
            }
            //right
            if (c+1 < maze[0].length && !bt[r][c+1] && maze[r][c+1] != 'W')
            {
                bt[r][c+1] = true;
                Point[] np = new Point[s.length+1];
                for (int k = 0; k < np.length-1; k++) {
                    np[k] = s[k];
                }
                np[np.length-1] = new Point(r,c+1);
                queue.add(np);
            }
        }
        return -1;
    }
    public static String breadthFirstSearch_Unweighted (String[] edges, String vertices, char start, char end)
    {
        {
            boolean[] bt = new boolean[vertices.length()];
            DS8_Queue<char[]> queue = new DS8_Queue<>();
            queue.add(new char[] {start});
            bt[vertices.indexOf(start)] = true;

            while (!queue.isEmpty())
            {
                char[] path = queue.poll();
                char lastLetter = path[path.length-1];
                bt[vertices.indexOf(lastLetter)] = true;

                if (lastLetter == end)
                    return new String(path);
                DS8_Queue<Character> add = new DS8_Queue<>();
                for (int i = 0; i < edges.length; i++) {
                    if (edges[i].contains(lastLetter +""))
                    {
                        char next;
                        if (edges[i].startsWith(lastLetter +""))
                            next = edges[i].charAt(1);
                        else next = edges[i].charAt(0);

                        if (!bt[vertices.indexOf(next)])
                            add.add(next);
                        if (!bt[vertices.indexOf(next)]){
                            bt[vertices.indexOf(next)] = true;
                            char[] newPath = new char [path.length+1];
                            for (int k = 0; k < path.length; k++) {
                                newPath[k] = path[k];
                            }
                            newPath[newPath.length-1] = add.poll();
                            queue.add(newPath);
                        }
                    }
                }
            }
            return null;
        }
    }
}
