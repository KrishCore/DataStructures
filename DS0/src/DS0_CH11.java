public class DS0_CH11
{
    public static boolean isSolvable(char[][] maze, boolean[][] beenThere, int col, int row) {
        if (row >= 0 && col >= 0 && row <= maze.length - 1 && col <= maze[0].length - 1) {
            if (maze[row][col] != 'W' && !beenThere[row][col]) {
                if (maze[row][col] == 'E') {
                    return true;
                } else {
                    beenThere[row][col] = true;
                    boolean u = isSolvable(maze, beenThere, col - 1, row);
                    boolean r = isSolvable(maze, beenThere, col, row - 1);
                    boolean d = isSolvable(maze, beenThere, col + 1, row);
                    boolean l = isSolvable(maze, beenThere, col, row + 1);
                    return u || r || d || l;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] maze = new char[][]{{'-', 'S', '-', '-', '-', '-'}, {'-', '-', '-', '-', 'E', '-'}};
        boolean[][] bools = new boolean[][]{{false, false, false, false, false, false}, {false, false, false, false, false, false}};
        System.out.println(isSolvable(maze, bools, 1, 0));
    }


}
