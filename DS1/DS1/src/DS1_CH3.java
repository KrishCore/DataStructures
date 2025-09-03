import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DS1_CH3
{
    public static int[] rowSums(String fileName)
    {
        int[] row = new int[0];
        ArrayList <Integer> nr = new ArrayList<>();
        ArrayList <Integer> num = new ArrayList<>();
        String[] rc = new String[0];
        try {
            File fileRef = new File(fileName);
            Scanner fileSc = new Scanner(fileRef);
            while (fileSc.hasNextLine()) {
                // scanner for each specific line
                String firstLine = fileSc.nextLine();
                rc = fileSc.next().split("X");
                nr.add(fileSc.nextInt());


//                	Scanner fromFile = new Scanner(new File("Files\\Final_Grades_v1.txt"));
//
//			// Reads the first number in the file. This is the number of grade that will follow
//			int numItems = fromFile.nextInt();
            }
            row = new int[(int) (rc[1])];
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                int total = 0;
                Scanner lineSc = new Scanner(line).useDelimiter(" ");
                while (lineSc.hasNextInt()) {
                    total += lineSc.nextInt();
                }

                num.add(total);
                lineSc.close();
            }
            for (int i = 0; i < num.size(); i++) {
                row[i] = num.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
//    public static int[] columnSums (String fileName)
//    {
//
//    }
}
