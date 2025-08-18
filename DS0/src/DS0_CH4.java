public class DS0_CH4
{
    public static boolean balanced(int[][] grid)
    {
        int sum1 = 0;
        int sum2 = 0;
        boolean ot = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == j)
                    ot  = false;
                if (ot)
                    sum1 += grid[i][j];
                else
                    sum2 += grid[i][j];
            }
            System.out.println(sum1 +"\n" + sum2);
            ot = true;
        }
        return sum1 == sum2;
    }
}