/**
 * This program asks the user how many questions are on a test,and it writes a
 * text file storing the grade for each possible correct answer total for that test.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GradeSheet
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter the number of questions on your test: ");
        int numQ = keyboard.nextInt();

        try
        {
            // Creates a new File reference and creates the file if it does not exist
            File file = new File("Files\\"+numQ+".txt");
            if(!file.exists())
                file.createNewFile();

            // Creates the PrintWriter
            FileWriter fw = new FileWriter(file,false);
            PrintWriter pw = new PrintWriter(fw);

            //writes all the possible correct totals and their scores to the text file
            for(int x = numQ; x>=0; x--)
                pw.printf("%d/%d - %.0f\n",x,numQ,x/(double)numQ*100);

            pw.close();
        }
        catch(Exception e)
        {
            System.out.println("Error while writing to file:");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
