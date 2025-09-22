import java.io.File;
import java.util.Scanner;

public class DS2_Printer_Queue
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter job file name:");
        String fileName = scan.next();

        MyQueue queue = new MyQueue();
        int jobNum = 1; //number of lines in the file

        try {
            File file = new File(fileName);
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine())
            {
                String line = fs.nextLine();
                String[] arr = line.split(",");
                int subTime = Integer.parseInt(arr[0]);
                int numPages = Integer.parseInt(arr[1]);

                Job job = new Job(jobNum++, subTime, numPages);
                queue.offer(job);
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

        while (!queue.isEmpty())
        {
            Job cJob = (Job) queue.poll();
            tJobs++;
            tPages += cJob.getPages();

            if (currentTime < cJob.getSubmissionTime()) {
                idleTime += cJob.getSubmissionTime() - currentTime;
                currentTime = cJob.getSubmissionTime();
            }
            

        }
    }
}
