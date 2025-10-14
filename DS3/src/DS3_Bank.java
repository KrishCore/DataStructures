import java.awt.image.PackedColorModel;
import java.io.File;
import java.util.Scanner;

public class DS3_Bank
{
    public static void main(String[] args)
    {
        System.out.print("Enter the file name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        System.out.println();
        DS3_Map<Integer, Double> map = new DS3_Map<>();

        try {
            File file = new File(fileName);
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine())
            {
                String line = fs.nextLine();
                String[] s = line.split(" ");
                int accountNum = Integer.parseInt(s[1]);
                String action = s[0];

                System.out.println(line);

                if (action.equals("OPEN")) {
                    if (!map.containsKey(accountNum)) {
                        map.put(accountNum, 0.0);
                        System.out.println("\tAccount " + s[1] + " opened with balance " + map.get(accountNum));
                    }
                    else System.out.println("\tAccount " + accountNum + " already exists");
                }
                else if (action.equals("DEPOSIT"))
                {
                    if (!map.containsKey(accountNum)) {
                        System.out.println("\tAccount not found for deposit into " + accountNum);
                    }
                    else {
                        map.put(accountNum, Double.parseDouble(s[2]));
                        System.out.println("\tDeposited " + Double.parseDouble(s[2]) + " into " + accountNum + ", new balance " + map.get(accountNum));
                    }
                } else if (action.equals("WITHDRAW")) {
                    if (map.containsKey(accountNum)) {
                        if (map.get(accountNum) != null && map.get(accountNum) - Double.parseDouble(s[2]) <= 0)
                            System.out.println("\tInsufficient funds for withdrawal from " + accountNum);
                        else if (map.get(accountNum) != null) {
                            map.put(accountNum, map.get(accountNum) - Double.parseDouble(s[2]));
                            System.out.println("\tWithdrew " + Double.parseDouble(s[2]) + " from " + accountNum + ", new balance " + map.get(accountNum));
                        }
                    }
                    else System.out.println("\tAccount not found for withdrawal from " + accountNum);
                } else if (action.equals("CLOSE")) {
                    if (map.containsKey(accountNum))
                        System.out.println("\tAccount " + accountNum + " closed");
                    else System.out.println("\tAccount not found for closing " + accountNum);
                    map.remove(accountNum);
                }
//                System.out.println("\t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
