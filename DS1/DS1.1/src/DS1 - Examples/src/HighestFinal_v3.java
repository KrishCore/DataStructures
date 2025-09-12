// This program reads grades from Final_Grades_v3.txt
// There will be an unknown number lines in the file
// Each line will store an unknown number of grades
// Each grade will be seperated by a * or a %

import java.util.*;
import java.io.*;

public class HighestFinal_v3
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
				// Reads in a line of grades and lets the Scanner know to delimit by '*' and '%'
				Scanner fromText = new Scanner(fromFile.nextLine()).useDelimiter("[*%]");
				while(fromText.hasNextInt())
					grades.add(fromText.nextInt());
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