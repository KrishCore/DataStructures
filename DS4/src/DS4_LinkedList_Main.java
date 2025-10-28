
import java.util.Scanner;

public class DS4_LinkedList_Main
{
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS4_LinkedList<Integer> ll = new DS4_LinkedList<Integer>();
        do
        {
            System.out.print("\n-Menu-\n");
            System.out.println("1. Size");
            System.out.println("2. Print");
            System.out.println("3. Print First Data");
            System.out.println("4. Print Last Data");
            System.out.println("5. Get Location");
            System.out.println("6. Remove First");
            System.out.println("7. Remove Last");
            System.out.println("8. Remove X");
            System.out.println("9. Add First");
            System.out.println("10. Add Last");
            System.out.println("11. Insert");
            System.out.println("12. Set X");
            System.out.println("13. Is Empty");
            System.out.println("14. Clear");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");
            choice= keyboard.nextInt();
            System.out.println();

            if(choice==1)
            {
                System.out.println("The list contains "+ll.size() + " items.");
            }
            if(choice==2)
            {
                System.out.println("The list contains " +ll);
            }
            if(choice==3)
            {

                System.out.println("The data of first is "+ll.getFirst());
            }
            if(choice==4)
            {
                System.out.println("The data of last is "+ll.getLast());
            }
            if(choice==5)
            {
                System.out.println("Enter an index: ");
                int index = keyboard.nextInt();
                System.out.println("The result of your get call is "+ll.get(index));
            }
            if(choice==6)
            {
                System.out.println("The result of your removeFirst call is "+ll.removeFirst());
            }
            if(choice==7)
            {
                System.out.println("The result of your removeLast call is "+ll.removeLast());
            }
            if(choice==8)
            {
                System.out.println("Enter an index: ");
                int index = keyboard.nextInt();
                System.out.println("The result of your remove call is "+ll.remove(index));
            }
            if(choice==9)
            {
                System.out.print("Enter the value you want to add: ");
                int v = keyboard.nextInt();
                ll.addFirst(v);
            }
            if(choice==10)
            {
                System.out.print("Enter the value you want to add: ");
                int v = keyboard.nextInt();
                ll.addLast(v);
            }
            if(choice==11)
            {
                System.out.print("Enter the value you want to insert: ");
                int v = keyboard.nextInt();
                System.out.print("Enter an index: ");
                int index = keyboard.nextInt();
                ll.add(index,v);
            }
            if(choice==12)
            {
                System.out.print("Enter the replacement value: ");
                int v = keyboard.nextInt();
                System.out.print("Enter an index: ");
                int index = keyboard.nextInt();

                System.out.println(ll.set(index,v) + " was replaced with "+v+".");
            }
            if(choice==13)
            {
                System.out.println("The result of your isEmpty call is "+ll.isEmpty());
            }
            if(choice==14)
            {
                ll.clear();
            }
            if(choice==0)
                break;

        }while(true);

        System.out.print("\nGood Bye!!");

    }
}
