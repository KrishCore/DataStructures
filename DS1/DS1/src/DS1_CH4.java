import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DS1_CH4
{
    public static int largestAreaSum (String fileName)
    {
        ArrayList <Integer> num = new ArrayList<>();
        int[][] grid1;     // = null; //new int[0][0];

        try {
            File fileRef = new File(fileName);
            Scanner fileSc = new Scanner(fileRef);
//            String[] size = fileSc.nextLine().split("X");
//            int c = Integer.parseInt(size[0]);
//            int r = Integer.parseInt(size[1]);
            String gridS = "";

            // read each line
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();

                // scanner for each specific line
                Scanner lineSc = new Scanner(line).useDelimiter("[ ,]");

                // adds each in tto a mega string
                while (lineSc.hasNextInt()) {
                    gridS += lineSc.nextInt() + " ";
                }
                gridS += "\n";
                lineSc.close();
            }

            // splits each int value into an array
            String[] line = gridS.split("\n");
            String[][] numb = new String[line.length][line[0].length()];
            int[][] grid = new int[line.length][line[0].length()];
            for (int i = 0; i < line.length; i++) {
                numb[i] = line[i].split(" ");
                System.out.println(Arrays.toString(numb[i]));
            }

            // copies string values to an int array
//            int r = grid.length;
//            int c = grid[0].length;
            for (int i = 0; i < line.length; i++) {
                for (int j = 0; j < line[0].length(); j++) {
                    grid[i][j] = Integer.parseInt(numb[i][j]);
                }
            }

            // adds the sides up and puts it in an arraylist
            for (int i = 0; i < line.length; i++) {
                for (int j = 0; j < line[0].length(); j++) {
                    if (i == 0) // if on top
                    {
                        if (j == 0) // if top left
                            num.add(grid[i][j] + grid[i+1][j] + grid[i][j+1]);
                        else if (j == line[0].length()-1) // if top right
                            num.add(grid[i][j] + grid[i+1][j] + grid[i][j-1]);
                    } else if (i == line.length - 1) // if bottom
                    {
                        if (j == 0) // if bottom left
                            num.add(grid[i][j] + grid[i-1][j] + grid[i][j+1]);
                        else if (j == line[0].length()-1) // if bottom right
                            num.add(grid[i][j] + grid[i-1][j] + grid[i][j-1]);
                    } // if none of the above (centers)
                    else num.add(grid[i][j] + grid[i-1][j] + grid[i+1][j] + grid[i][j+1] + grid[i][j-1]);
                }
            }
            grid1 = grid;
            fileSc.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        // finds the highest value in the arraylist of sums
        int max = 0;       //grid1[0][0]; // + grid1[1][0] + grid1[0][1];
        for (int i = 0; i < num.size(); i++) {
            if (max < num.get(i))
                max = num.get(i);
        }
        return max;
    }
}
