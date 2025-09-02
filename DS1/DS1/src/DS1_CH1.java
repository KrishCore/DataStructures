import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DS1_CH1
{
    public static int[] sumLines (String fileName){
        File fileRef = new File(fileName);
        ArrayList <Integer> num = new ArrayList<>();
        try {
            Scanner sc = new Scanner(fileRef);

//            while(fromFile.hasNextLine())
//			{
//				// Reads in a line of grades and lets the Scanner know to delimit by '*' and '%'
//				Scanner fromText = new Scanner(fromFile.nextLine()).useDelimiter("[*%]");
//				while(fromText.hasNextInt())
//					grades.add(fromText.nextInt());
//			}

            while (sc.hasNextLine()) {
                Scanner scan = new Scanner(sc.nextLine()).useDelimiter("[ ,]");
                String star = sc.nextLine();
                String[] split = star.split("[ ,]");
                int total = 0;
                for (int i = 0; i < split.length; i++) {
                    total += scan.nextInt();
                }
                for (int i = 0; i < split.length; i++) {
                    System.out.print(split[i] + " ");
                }
                num.add(total);
                while (scan.hasNextInt()) {
                    System.out.print(scan.nextInt() + "  ");
                }
            }
            System.out.println("\n__" + num);
        } catch (Exception e) {
           e.printStackTrace();
        }
//        finally {
//            int[] arr = new int[num.size()];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = num.get(i);
//            }
//        }
        int[] arr = new int[num.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = num.get(i);
        }

        return arr;
    }
}
