
import java.util.Scanner;

public class DS4_Doubly_Circular_LinkedList_Main
{
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS4_Doubly_Circular_LinkedList<String> ll = new DS4_Doubly_Circular_LinkedList<String>();
        do
        {
            System.out.print("\n-Menu-\n");
            System.out.println("1. Print Forward");
            System.out.println("2. Print Backward");
            System.out.println("3. Print First");
            System.out.println("4. Print Last");
            System.out.println("5. Get Location");
            System.out.println("6. Remove First");
            System.out.println("7. Remove Last");
            System.out.println("8. Remove X");
            System.out.println("9. Add First");
            System.out.println("10. Add Last");
            System.out.println("11. Insert");
            System.out.println("12. Set X");
            System.out.println("13. Size");
            System.out.println("14. Is Empty");
            System.out.println("15. Clear");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");
            choice= keyboard.nextInt();

            System.out.println();
            if(choice==1)
            {
                System.out.println("The list printed forwards contains: "+ll.toString());
            }
            if(choice==2)
            {
                System.out.println("The list printed backwards contains: "+ll.backwardsToString());
            }
            if(choice==3)
            {
                System.out.println("The result of your getFirst call is "+ll.getFirst());
            }
            if(choice==4)
            {
                System.out.println("The result of your getFast call is "+ll.getLast());
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
                System.out.print("Enter the text you want to add: ");
                String text = keyboard.next();
                ll.addFirst(text);
            }
            if(choice==10)
            {
                System.out.print("Enter the text you want to add: ");
                String text = keyboard.next();
                ll.addLast(text);
            }
            if(choice==11)
            {
                System.out.print("Enter the text you want to insert: ");
                String text = keyboard.next();
                System.out.println("Enter an index: ");
                int index = keyboard.nextInt();
                ll.add(index,text);
            }
            if(choice==12)
            {
                System.out.print("Enter the replacement text: ");
                String text = keyboard.next();
                System.out.println("Enter an index: ");
                int index = keyboard.nextInt();

                System.out.println(ll.set(index,text) + " was replaced with "+ text+".");
            }
            if(choice==13)
            {
                System.out.println("The result of your size call is "+ll.size());
            }
            if(choice==14)
            {
                System.out.println("The result of your isEmpty call is "+ll.isEmpty());

            }
            if(choice==15)
            {
                ll.clear();
            }
            if(choice==0)
                break;

        }while(true);

        System.out.print("\nGood Bye!!");
    }
}
