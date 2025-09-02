public class DS0_CH12
{
    public static void floodFill (char[][] grid, int col, int row, char toReplace, char newValue)
    {
        System.out.println(grid[row][col]);
        if (grid[row][col] == toReplace) {
            grid[row][col] = newValue;
            if (row != 0 && grid[row - 1][col] == toReplace)
                floodFill(grid, col, row - 1, toReplace, newValue);
            if (col != 0 && grid[row][col - 1] == toReplace)
                floodFill(grid, col - 1, row, toReplace, newValue);
            if (row != grid.length - 1 && grid[row + 1][col] == toReplace)
                floodFill(grid, col, row + 1, toReplace, newValue);
            if (col != grid[row].length - 1 && grid[row][col + 1] == toReplace)
                floodFill(grid, col + 1, row, toReplace, newValue);
        }
    }
}
