
import java.util.*;
public class SetMain
{
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS3_Set<Integer> set= new DS3_Set<Integer>();

        do
        {
            System.out.print("\n1. Add\n2. Contains\n3. Remove\n4. Print (with iterator)\n5. Empty?\n6. Clear\n7. Size\n0. Exit\nEnter selection: ");
            choice= keyboard.nextInt();

            if(choice==1)
            {
                System.out.print("\nEnter a value: ");
                int enter= keyboard.nextInt();

                System.out.println("\nThe result of your add call is "+set.add(enter));

            }
            if(choice==2)
            {
                System.out.print("\nEnter a value: ");
                int enter= keyboard.nextInt();

                System.out.println("\nThe result of your contains call is "	+set.contains(enter));
            }
            if(choice==3)
            {
                System.out.print("\nEnter a value: ");
                int enter= keyboard.nextInt();
                System.out.println("\nThe result of your remove call is "+set.remove(enter));
            }
            if(choice==4)
            {
                if(set.isEmpty())
                    System.out.println("\nThe set is empty.");
                else
                {
                    Iterator<Integer> iter= set.iterator();

                    System.out.println("\nThe set contains:");

                    while(iter.hasNext())
                    {
                        Integer rvb= (Integer)(iter.next());
                        System.out.print(rvb+ " ");
                    }
                    System.out.print("\n");
                }
            }
            if(choice==5)
            {
                System.out.println("\nThe result of your isEmpty call is "+set.isEmpty());
            }
            if(choice==6)
            {
                set.clear();
                System.out.println("\nClear was called");
            }
            if(choice==7)
            {
                System.out.println("\nThe result of your size call is "+set.size());
            }
            if(choice==0)
            {
                break;
            }

        }while(true);

        System.out.println("\nGood Bye!!");
    }
}
