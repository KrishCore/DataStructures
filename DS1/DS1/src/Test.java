import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            File file = new File("test.txt");
            if (!file.exists()) file.createNewFile();

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Scanner sct = new Scanner(sc.nextLine()).useDelimiter("[* ]");
                while (sct.hasNextInt())
                    System.out.println(sct.nextInt());
            }

            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < 5; i++) {
                pw.println((int) (Math.random()*100));
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
