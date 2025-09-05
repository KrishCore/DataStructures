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
//        finally {
//            int[] arr = new int[num.size()];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = num.get(i);
//            }
//        }
        int total = 0;
        for (int i = 0; i < num.size(); i++) {
            total += num.get(i);
        }

        return total;
    }
}
