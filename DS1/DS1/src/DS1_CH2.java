

import java.util.Scanner;
import java.io.File;
public class DS1_CH2
{
    public static int vowelNames (String fileName)
    {
        int vowelCount = 0;
        try {
            File fileRef = new File(fileName);
            Scanner fileSc = new Scanner(fileRef);
            while (fileSc.hasNextLine())
            {
                Scanner lineSc = new
                        Scanner(fileSc.nextLine()).useDelimiter("[ ,]");
                while (lineSc.hasNext())
                {
                    String s = lineSc.next();
                    System.out.println(s);
                    if ("AEIOUaeiou".contains(s.charAt(0)+""))
                        vowelCount++;
                }
                lineSc.close();
            }
            fileSc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vowelCount;
    }
}
