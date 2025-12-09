
import java.util.*;
public class DS6_HeapMain
{
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS6_MaxHeap<Integer> heap= new DS6_MaxHeap<Integer>();

        do
        {
            System.out.print("\n-Menu-\n1. Print\n2. Size\n3. Empty?\n4. Insert\n5. Remove\n6. Clear\n0. Exit\nEnter: ");
            choice= keyboard.nextInt();

            if(choice==1)
            {
                System.out.println("\nThe data in the heap is: " + heap);
            }
            if(choice==2)
            {
                System.out.println("\nThe size of the heap is " + heap.size());
            }
            if(choice==3)
            {
                if(heap.isEmpty()==true)
                    System.out.println("\nThe heap is empty.");
                else
                    System.out.println("\nThe heap has data.");
            }
            if(choice==4)
            {
                System.out.print("\nEnter a value to add: ");
                int item = keyboard.nextInt();
                System.out.println(item+" was added to the heap ");
                heap.insert(item);
            }
            if(choice==5)
            {
                if(heap.isEmpty())
                    System.out.println("\nThe is no data to remove.");
                else
                {
                    Integer i = heap.remove();
                    System.out.println("\n"+i + " was removed from the heap.");
                }
            }
            if(choice==6)
            {
                System.out.println("\nThe heap has been cleared.");
                heap.clear();
            }
            if(choice==0)
                break;

        }while(true);

        System.out.print("\nGood Bye!!");


    }
}
