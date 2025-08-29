import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DS1_CH1
{
    public static int[] sumLines (String fileName)
    {
        File fileRef = new File(fileName);
        ArrayList <Integer> num = new ArrayList<>();
        try {
            Scanner sc = new Scanner(fileRef);
            int i = 0;
            while (sc.hasNextLine()) {
                Scanner scan = new Scanner(sc.nextLine()).useDelimiter("[,]");
                while (scan.hasNextInt()) {
                    num.add(scan.nextInt());
                    System.out.print(scan.nextInt() + "  ");
                }
            }
            for (int j = 0; j < num.size(); j++) {
                System.out.println(num.get(i));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }


        return new int[] {1};
    }
}
