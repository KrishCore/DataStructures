// This program reads grades from Final_Grades_v3.txt
// There will be an unknown number lines in the file
// Each line will store an unknown number of grades
// Each grade will be seperated by a * or a %

import java.util.*;
import java.io.*;

public class HighestFinal_v4
{
    public static void main(String[] args)
    {
        ArrayList<Integer> grades = new ArrayList<>();

        try
        {
            // Creates a new File and passes it to the Scanner
            Scanner fromFile = new Scanner(new File("Files\\Final_Grades_v3.txt"));


            while(fromFile.hasNextLine())
            {
                // Sets the starting highest grade to a number is lower than any possible grade
                int highest = -1;

                // Reads in a line of grades
                Scanner fromText = new Scanner(fromFile.nextLine());

                /* Reads in a line of text and then separates out the
                grades into Strings using '*' and '%' as delimiters
                */
                String[] data = fromText.nextLine().split("[*%]");

                for(int x=0; x<data.length; x++)
                    // Turns a String from data into an int
                    grades.add(Integer.parseInt(data[x]));

            }
        }
        catch(Exception e)
        {
            System.out.println("Error while reading from file:");
            e.printStackTrace();
            System.exit(0);
        }

        // Sets the starting highest grade to a number is lower than any possible grade
        int highest = -1;

        // finds the highest grade
        for(int x=0; x<grades.size(); x++)
        {
            int grade = grades.get(x);
            if(grade>highest)
                highest = grade;
        }
        // Displays the highest grade
        System.out.println("The highest grade was " +highest+ ".");
    }
}