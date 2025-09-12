import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DS1_CH3
{
    public static int[] rowSums(String fileName)
    {
        ArrayList <Integer> num = new ArrayList<>();

        try {
            File fileRef = new File(fileName);
            Scanner fileSc = new Scanner(fileRef);

            // read each line
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();

                // scanner for each specific line
                Scanner lineSc = new Scanner(line).useDelimiter("[ ,]");

                int total = 0;
                while (lineSc.hasNextInt()) {
                    total += lineSc.nextInt();
                }

                num.add(total);
                lineSc.close();
            }

            fileSc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] row = new int[num.size()-1];
        for (int i = 0; i < row.length; i++) {
            row[i] = num.get(i+1);
        }
        return row;
    }

    public static int[] columnSums (String fileName)
    {
        ArrayList <Integer> num = new ArrayList<>();

        int[] col = new int[0];
        try {
            File fileRef = new File(fileName);
            Scanner fileSc = new Scanner(fileRef);

//            4X4
//            1   2   3   4
//            2   3   4   5
//            6   1   3   2
//            3   4   5   6

            String line = fileSc.nextLine();
            int[] f = new int[]{Integer.parseInt(line.split("X")[0])};
            int n = f[0];
            col = new int[n];

            // read each line
            while (fileSc.hasNextLine()) {
                line = fileSc.nextLine();
                // scanner for each specific line
                Scanner lineSc = new Scanner(line).useDelimiter("[ ,]");

                int total = 0;
                int i = 0;
                while (lineSc.hasNextInt()) {
                    col[i] += lineSc.nextInt();
                    i++;
                    i %= n;
                }

                num.add(total);
                lineSc.close();
            }

            fileSc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < col.length; i++) {
//            col[i] = num.get(i+1);
//        }
        return col;
    }
}
