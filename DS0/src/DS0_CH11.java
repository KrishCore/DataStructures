public class DS0_CH11
{
    public static boolean isSolvable(char[][] maze, boolean[][] beenThere, int col, int row) {
        if (row >= 0 && col >= 0 && row <= maze.length - 1 && col <= maze[0].length - 1) {
            if (maze[row][col] != 'W' && !beenThere[row][col]) {
                if (maze[row][col] == 'E') {
                    return true;
                } else {
                    beenThere[row][col] = true;
                    return  isSolvable(maze, beenThere, col - 1, row)
                            || isSolvable(maze, beenThere, col, row - 1)
                            || isSolvable(maze, beenThere, col + 1, row)
                            || isSolvable(maze, beenThere, col, row + 1);
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
