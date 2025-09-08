import java.io.File;
import java.util.Scanner;

public class DS1_CH4 {

    public static int largestAreaSum(String fileName)
    {
        try {
            File fileRef = new File(fileName);
            Scanner scanner = new Scanner(fileRef);
            String[] size = scanner.nextLine().split("X");
            int c = Integer.parseInt(size[0].trim());
            int r = Integer.parseInt(size[1].trim());

            int[][] grid = new int[r][c];
            for (int row = 0; row < r; row++) {
                for (int col = 0; col < c; col++) {
                    grid[row][col] = scanner.nextInt();
                }
            }
            scanner.close();

            int maxAreaSum = Integer.MIN_VALUE;
            // loop through all cells
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    int areaSum = grid[i][j];


                    if (i > 0) areaSum += grid[i - 1][j];
                    if (i < r - 1) areaSum += grid[i + 1][j];
                    if (j > 0) areaSum += grid[i][j - 1];
                    if (j < c - 1) areaSum += grid[i][j + 1];

                    if (areaSum > maxAreaSum)
                        maxAreaSum = areaSum;
                }
            }
            return maxAreaSum;

        } catch (Exception e) {
            // if something goes wrong, just return -1 like before
            return -1;
        }
    }
}
