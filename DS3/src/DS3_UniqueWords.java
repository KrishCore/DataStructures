import java.io.File;
import java.util.ArrayList;
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
                ArrayList<String> arr = new ArrayList<>();
                for (int i = 0; i < lin.length; i++)
                    arr.add(lin[i]);

                for (int i = 0; i < arr.size(); i++) {
                    if (!arr.get(i).isEmpty() && !arr.get(i).isBlank() && !(arr.get(i).equals(""))) {
                        char[] chars = arr.get(i).toCharArray();
                        String string = "";
                        for (int j = 0; j < chars.length; j++)
                            if (!".,!?;:\"'’-–\n ()".contains(chars[j] + "") && !Character.isWhitespace(chars[j]))
                                string += chars[j];

                        arr.set(i, string);
                        if (!string.isEmpty())
                            set.add(arr.get(i));
                    }
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
