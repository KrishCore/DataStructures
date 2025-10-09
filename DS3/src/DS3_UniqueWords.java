import java.io.File;
import java.util.Arrays;
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

                System.out.println("\0");
                for (int i = 0; i < lin.length; i++) {


                    if (!lin[i].isEmpty() || !lin[i].isBlank() || !(lin[i].equals(""))) {
                        char[] chars = lin[i].toCharArray();
                        System.out.println("_"+ Arrays.toString(chars) +"_");
//                        error is the blanks are punctuation
                        // for test10
                        //_["]_
                        //truetruetruetrue[] ___
                        //_[–]_
                        //truetruetruetrue[] ___
                        System.out.print(!lin[i].isEmpty() + "" + !lin[i].isBlank() + !(lin[i].equals("")) + (chars.length>0) + "[] ");
                        lin[i] = "";
                        if (!".,!?;:\"'’-–\n ()".contains(chars[0]+"")) {
                            for (int j = 0; j < chars.length; j++) {
                                System.out.print(Character.isWhitespace(chars[j]) + "--");
                                if (!".,!?;:\"'’-–\n ()".contains(chars[j] + "") && !Character.isWhitespace(chars[j])) {
                                    System.out.print((chars[j] == '\0' && "\0".contains(chars[j] + "")) + "  ");
                                    lin[i] += chars[j];
                                    System.out.print(chars[j] + "_");
                                }
                            }
                        }

                        set.add(lin[i]);
                        System.out.println("___"+lin[i]);
                    }
                }

                for (int i = 0; i < lin.length; i++) {
//                    set.add(lin[i]);
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
