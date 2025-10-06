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
                Scanner ls = new Scanner(line).useDelimiter(" ");
                String string = ls.next();

                for (int i = 0; i < lin.length; i++) {


                    for (int k = 0; k < lin[i].length(); k++) {
                        if (".,!?;:\"\'-".contains(lin[i].charAt(k)+""))
                        {
                            if (k ==0) {
                                lin[i] = (lin[i].substring(1));
                            }
                            else if (k ==lin[i].length()-1) {
                                lin[i] = (lin[i].substring(0, lin[i].length()-2));
                            }
                            else if (k>0 && k <string.length()-1) {
                                lin[i] = (lin[i].substring(0, k) + lin[i].substring(k +1));
                            }
                        }
//                        else set.add(string);
                    }
                }

                while (ls.hasNext()) {
                    set.add(string.toLowerCase());
                    ls.next();
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
