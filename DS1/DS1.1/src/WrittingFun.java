import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WrittingFun
{
    public static void main(String[] args)
    {
        try {
            File file = new File("Files\\numbersAndLetter.txt");
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);

            pw.println((int) (Math.random()*1000) + " " + (char) (Math.random()*26 +'A'));
            pw.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
