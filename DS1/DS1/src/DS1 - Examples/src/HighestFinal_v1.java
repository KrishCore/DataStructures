// This program reads grades from Final_Grades_v1.txt
// The first line stores how many grades will follow
// Each grade will be on its own line

import java.util.*;
import java.io.*;

public class HighestFinal_v1
{
	public static void main(String[] args)
	{
		ArrayList<Integer> grades = new ArrayList<>();

		try
		{
			// Creates a new File and passes it to the Scanner
			Scanner fromFile = new Scanner(new File("Files\\Final_Grades_v1.txt"));

			// Reads the first number in the file. This is the number of grade that will follow
			int numItems = fromFile.nextInt();

			// loops once for each expected grade
			for(int x=0; x<numItems; x++)
				grades.add(fromFile.nextInt());
		}
		catch(Exception e)
		{
			// Displays that there was an error and what the error was
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