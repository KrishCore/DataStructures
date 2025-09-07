import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DS1_CH4
{
    public static int largestAreaSum (String fileName)
    {
        ArrayList <Integer> num = new ArrayList<>();

        try {
            File fileRef = new File(fileName);
            Scanner fileSc = new Scanner(fileRef);
            String[] size = fileSc.nextLine().split("X");
            int c = Integer.parseInt(size[0]);
            int r = Integer.parseInt(size[1]);

            int[][] grid = new int[r][c];

            // read each line
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();

                // scanner for each specific line
                Scanner lineSc = new Scanner(line).useDelimiter("[ ,]");

                int row = 0;
                int col = 0;
                while (lineSc.hasNextInt() && col < c) {
                    grid[row][col] = lineSc.nextInt();
                    System.out.println(grid[row][col]);
                    c++;
                }
                r++;
                lineSc.close();
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (i == 0)
                    {
                        if (j == 0)
                            num.add(grid[i][j] + grid[i+1][j] + grid[i][j+1]);
                        else if (j == c-1)
                            num.add(grid[i][j] + grid[i+1][j] + grid[i][j-1]);
                    } else if (i == r - 1)
                    {
                        if (j == 0)
                            num.add(grid[i][j] + grid[i-1][j] + grid[i][j+1]);
                        else if (j == c-1)
                            num.add(grid[i][j] + grid[i-1][j] + grid[i][j-1]);
                    }
                    else num.add(grid[i][j] + grid[i-1][j] + grid[i+1][j] + grid[i][j+1] + grid[i][j-1]);
                }
            }
            fileSc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            int[] arr = new int[num.size()];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = num.get(i);
//            }
//        }
        int max = 0;
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i) < max)
                max = num.get(i);
        }
        return max;
    }
}
