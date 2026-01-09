
import java.util.Random;
import java.util.Scanner;

public class DS7_SpeedTests
{
    public static void main(String[] args)
    {
        Random randGen = new Random();
        Scanner keyboard = new Scanner(System.in);
        boolean display	= false;
        System.out.println("Please enter the size of the array you would like to have sorted: ");
        int size  = keyboard.nextInt();
        String order ="";
        while(!order.equals("sorted") && !order.equals("unsorted"))
        {
            System.out.println("Do you want the data to start off sorted or unsorted (sorted/unsorted):");
            order = keyboard.next();
        }

        int[] data = new int[size];
        if(order.equals("sorted"))
        {
            // generate values for the data array
            int v=0;
            for(int i = 0;  i < size; i++)
            {
                data[i] = v+= randGen.nextInt(10)+1;
            }
        }
        else {
            // generate values for the data array
            for(int i = 0;  i < size; i++)
            {
                data[i] = randGen.nextInt(10000);
            }
        }
        long startTime = 0;
        long endTime = 0;
        double runTime =0;

        System.out.print("\n");

        int[] copy = copyArray(data);
        startTime = System.nanoTime();
        DS7_Sorts.selectionSort(copy);
        endTime = System.nanoTime();
        runTime = (double)(endTime-startTime)/1000000000L;

        System.out.printf("Selection sort took %.7f seconds to sort the array.\n",runTime);

        copy = copyArray(data);
        startTime = System.nanoTime();
        DS7_Sorts.insertionSort(copy);
        endTime = System.nanoTime();
        runTime = (double)(endTime-startTime)/1000000000L;

        System.out.printf("Insertion sort took %.7f seconds to sort the array.\n",runTime);

        copy = copyArray(data);
        startTime = System.nanoTime();
        DS7_Sorts.mergeSort(copy,0,data.length-1);
        endTime = System.nanoTime();
        runTime = (double)(endTime-startTime)/1000000000L;

        System.out.printf("Merge sort took %.7f seconds to sort the array.\n", runTime);

        copy = copyArray(data);
        startTime = System.nanoTime();
        DS7_Sorts.quickSort(copy,0,data.length-1);
        endTime = System.nanoTime();
        runTime = (double)(endTime-startTime)/1000000000L;

        System.out.printf("Quick sort took %.7f seconds to sort the array.\n",runTime);

        copy = copyArray(data);
        startTime = System.nanoTime();
        DS7_Sorts.heapSort(copy);
        endTime = System.nanoTime();
        runTime = (double)(endTime-startTime)/1000000000L;

        System.out.printf("Heap sort took %.7f seconds to sort the array.\n", runTime);
    }

    public static int[] copyArray(int[] data)
    {
        int[] data2 = new int[data.length];
        for(int i = 0;  i < data.length; i++)
        {
            data2[i] = data[i];
        }
        return data2;
    }
}
