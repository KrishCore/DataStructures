public class DS8_DFS
{
    public static boolean depthFirstSearch_Simple(char[][] maze)
    {
        /**Create a boolean array (visited) that has an element for each location in your graph or grid.
         Create a stack (stack) that grid positions
         Push the location of start to stack
         Mark start location as visited
         while(stack is not empty)
            Pop from stack and store it (location)
            If end is visited
                return true
            Push all adjacent locations that have not been visited to stack, adding each pushed location as visited.
         return false
         */

        int coll = maze[0].length;
        boolean[][] bt = new boolean[maze.length][maze[0].length];
        DS8_Stack<Integer> stack = new DS8_Stack<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S' || maze[i][j] == 's') {
                    stack.push(i* coll +j);
                    bt[i][j] = true;
                }
            }
        }
        while (!stack.isEmpty())
        {
            int s = stack.pop();
            int r = s / coll;
            int c = s % coll;

            if (maze[r][c] == 'E')
                return true;

            //up
            if (r-1 >= 0 && !bt[r-1][c] && maze[r-1][c] != 'W')
            {
                bt[r-1][c] = true;
                stack.push((r-1)* coll +c);
            }
            //down
            if (r+1 < maze.length && !bt[r+1][c] && maze[r+1][c] != 'W')
            {
                bt[r+1][c] = true;
                stack.push((r+1)* coll +c);
            }
            //left
            if (c-1 >= 0 && !bt[r][c-1] && maze[r][c-1] != 'W')
            {
                bt[r][c-1] = true;
                stack.push(r* coll +c-1);
            }
            //right
            if (c+1 < maze[0].length && !bt[r][c+1] && maze[r][c+1] != 'W')
            {
                bt[r][c+1] = true;
                stack.push(r* coll +c+1);
            }
        }
        return false;
    }

    public static boolean depthFirstSearch_Portals(char[][] maze)
    {
        int coll = maze[0].length;
        boolean[][] bt = new boolean[maze.length][maze[0].length];
        DS8_Stack<Integer> stack = new DS8_Stack<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S' || maze[i][j] == 's') {
                    stack.push(i* coll +j);
                    bt[i][j] = true;
                }
            }
        }
        while (!stack.isEmpty())
        {
            int s = stack.pop();
            int r = s / coll;
            int c = s % coll;

            if (maze[r][c] == 'E')
                return true;

            char start;
            if ("aAbBcCdD".contains(maze[r][c]+"")) {
                start = maze[r][c];
                if (Character.isUpperCase(start)) {
                    for (int i = 0; i < maze.length; i++) {
                        for (int j = 0; j < maze[0].length; j++) {
                            if (maze[i][j] == Character.toLowerCase(start) && !bt[i][j]) {
                                bt[i][j] = true;
                                stack.push(i*coll+j);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < maze.length; i++) {
                        for (int j = 0; j < maze[0].length; j++) {
                            if (maze[i][j] == Character.toUpperCase(start) && !bt[i][j]) {
                                bt[i][j] = true;
                                stack.push(i*coll+j);
                            }
                        }
                    }
                }
            }
            
            //up
            if (r-1 >= 0 && !bt[r-1][c] && maze[r-1][c] != 'W')
            {
                bt[r-1][c] = true;
                stack.push((r-1)* coll + c);
            }
            //down
            if (r+1 < maze.length && !bt[r+1][c] && maze[r+1][c] != 'W')
            {
                bt[r+1][c] = true;
                stack.push((r+1)* coll + c);
            }
            //left
            if (c-1 >= 0 && !bt[r][c-1] && maze[r][c-1] != 'W')
            {
                bt[r][c-1] = true;
                stack.push(r* coll + c-1);
            }
            //right
            if (c+1 < maze[0].length && !bt[r][c+1] && maze[r][c+1] != 'W')
            {
                bt[r][c+1] = true;
                stack.push(r* coll + c+1);
            }
        }
        return false;
    }
}
