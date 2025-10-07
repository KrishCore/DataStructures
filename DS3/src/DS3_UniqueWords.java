import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class DS3_UniqueWords
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = scan.next();
        System.out.println();
        DS3_Set<String> set = new DS3_Set<>();
        try {
            File file = new File(fileName);
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine())
            {
                String line = fs.nextLine().toLowerCase();
                String[] lin = line.split(" ");
//                Scanner ls = new Scanner(line).useDelimiter(" ");
//                String string = ls.next();

                for (int i = 0; i < lin.length; i++) {

                    char[] chars = lin[i].toCharArray();
                    lin[i] = "";
                    for (int j = 0; j < chars.length; j++) {
                        if (!".,!?;:\"\'’-–\n ()".contains(chars[j]+""))
                            lin[i] += chars[j];
                    }
                }

                for (int i = 0; i < lin.length; i++) {
                    set.add(lin[i]);
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
