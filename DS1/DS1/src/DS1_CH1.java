import java.io.File;
import java.util.Scanner;

public class DS1_CH1
{
    public static int[] sumLines (String fileName)
    {
        File fileRef = new File(fileName);
        try {
            Scanner sc = new Scanner(fileRef);
            int i = 0;
            while (sc.hasNextLine()) {
                Scanner scan = new Scanner(sc.nextLine()).useDelimiter("[,]");
                while (scan.hasNextInt())
                    
                    System.out.print(scan.nextInt() + "  ");
            }
        } catch (Exception e) {
           e.printStackTrace();
        }


        return new int[] {1};
    }
}
