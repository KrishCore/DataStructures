import java.io.File;
import java.util.Scanner;

public class DS2_Printer_Queue
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter job file name: ");
        String fileName = scan.nextLine();
        if (!fileName.contains(".txt")) //not really needed
            fileName+=".txt"; // this also
//        System.out.println(fileName);

        // Use MyQueue for storing jobs
        MyQueue queue = new MyQueue();

        try {
            File file = new File(fileName);
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                String[] arr = line.split(",");
                int subTime = Integer.parseInt(arr[0]);
                int numPages = Integer.parseInt(arr[1]);

                //add job data [submission time, pages]
                queue.offer(new int[] { subTime, numPages });
            }
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int currentTime = 0;
        int tPages = 0;
        int tWaitTime = 0;
        int tJobs = 0;
        int idleTime = 0;

        // goes through each job in the queue
        while (!queue.isEmpty()) {
            // delete the job (it's now an array of [submission time, pages])
            int[] job = (int[]) queue.poll();
            int subTime = job[0];
            int numPages = job[1];

            tJobs++;
            tPages += numPages;

            if (currentTime < subTime) {
                idleTime += subTime - currentTime;
                currentTime = subTime;
            }

            // Print "job received" message
            System.out.println("Time " + subTime + "s: Job #" + tJobs + " Received (" + numPages + " pages)");

            //buffering start
            System.out.println("Time " + currentTime + "s: Job #" + tJobs + " Buffering Started");
            currentTime += 3; // buffering takes 3 secs

            // buffering finish, start printing
            System.out.println("Time " + currentTime + "s: Job #" + tJobs + " Finished Buffering and Started Printing");
            tWaitTime += currentTime - subTime;

            // print the job (5 secs per page)
            currentTime += numPages * 5;

            // finished printing
            System.out.println("Time " + currentTime + "s: Job #" + tJobs + " Finished Printing\n");
        }

        // summary
        System.out.println("Printing Simulation Complete.");
        System.out.println("Total Print Jobs: " + tJobs);
        System.out.println("Total Pages: " + tPages);
        System.out.printf("Average Job Wait Time: %.1fs\n", (double) tWaitTime / tJobs);
        System.out.println("Idle Time: " + idleTime + "s");
    }
}