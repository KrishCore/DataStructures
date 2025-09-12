// This program reads grades from Final_Grades_v2.txt
// There will be an unknown number of grades in the file
// Each grade will be on its own line

import java.util.*;
import java.io.*;

public class HighestFinal_v2
{
	public static void main(String[] args)
	{
		ArrayList<Integer> grades = new ArrayList<>();

		try
		{
			// Creates a new File and passes it to the Scanner
			Scanner fromFile = new Scanner(new File("Files\\Final_Grades_v2.txt"));

			// loops until there is no data left or a non-int is encountered
			while(fromFile.hasNextInt())
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