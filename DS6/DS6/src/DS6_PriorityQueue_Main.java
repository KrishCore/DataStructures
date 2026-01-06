
import java.util.Scanner;

public class DS6_PriorityQueue_Main {
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS6_PriorityQueueInterface<Integer> pq= new DS6_PriorityQueue<>();

        do
        {
            System.out.print("\n-Menu-\n1. Print\n2. Size\n3. Empty?\n4. Add\n5. Remove\n6. Clear\n0. Exit\nEnter selection: ");
            choice= keyboard.nextInt();

            if(choice==1)
            {
                System.out.println("\nThe data in the priority queue is: " + pq);
            }
            if(choice==2)
            {
                System.out.println("\nThe size of the priority queue is " + pq.size());
            }
            if(choice==3)
            {
                if(pq.isEmpty()==true)
                    System.out.println("\nThe priority queue is empty.");
                else
                    System.out.println("\nThe priority queue has data.");
            }
            if(choice==4)
            {
                System.out.print("\nEnter number to be added: ");
                int item = keyboard.nextInt();
                pq.offer(item);
                System.out.println(item+" was added to the priority queue.");
            }
            if(choice==5)
            {
                if(pq.isEmpty()==true)
                    System.out.println("\nThe priority queue is empty.");
                else
                    System.out.println("\n"+pq.poll()+" was removed from the priority queue.");
            }
            if(choice==6)
            {
                pq.clear();
                System.out.println("\nThe priority queue has been cleared.");
            }
            if(choice==0)
                break;

        }while(true);

        System.out.print("\nGood Bye!!");

    }
}
