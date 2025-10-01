
import java.util.*;
public class MapMain
{
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS3_Map<Integer,String> map= new DS3_Map<Integer,String>();

        do
        {
            System.out.print("\n---Menu---\n1. Clear\n2. Contains key?\n3. Contains value?\n4. Get \n5. Put");
            System.out.print("\n6. Remove\n7. Size\n8. Print all (with set iterator)\n9. Empty?\n0. Exit\nEnter: ");
            choice= keyboard.nextInt();

            if(choice==1)
            {
                System.out.println("\nClear was called.");
                map.clear();
            }
            if(choice==2)
            {
                System.out.println("\nEnter an id : ");
                int id= keyboard.nextInt();
                System.out.println("\nThe result of your containKey call is "+map.containsKey(id));
            }
            if(choice==3)
            {
                System.out.print("\nEnter a name: ");
                String name= keyboard.next();
                System.out.println("\nThe result of your add containsValuye is "+map.containsValue(name));
            }
            if(choice==4)
            {
                System.out.println("\nEnter an id : ");
                int id= keyboard.nextInt();
                System.out.println("\nThe result of your get call is "+map.get(id));

            }
            if(choice==5)
            {
                System.out.print("\nEnter a name: ");
                String name=keyboard.next();
                System.out.print("Enter an id: ");
                int id=keyboard.nextInt();
                System.out.println("\nThe result of your put call is "+map.put(id,name));
            }
            if(choice==6)
            {
                System.out.println("\nEnter an id : ");
                int id= keyboard.nextInt();
                System.out.println("\nThe result of your remove call is "+map.remove(id));
            }
            if(choice==7)
            {
                System.out.println("\nThe result of your add call is "+map.size());
            }
            if (choice==8)
            {
                if(map.isEmpty())
                    System.out.println("\nThe map is empty.");
                else
                {
                    System.out.print("\nThe data in the map is: ");
                    Iterator<MapEnt<Integer,String>> iter= map.entrySet().iterator();
                    while(iter.hasNext())
                    {
                        MapEnt<Integer,String> item= iter.next();
                        System.out.print(item+" ");
                    }
                    System.out.println();
                }
            }
            if (choice==9)
            {
                System.out.println("\nThe result of your isEmpty call is "+map.isEmpty());
            }
            if (choice==0)
            {

                break;
            }

        }while(true);

        System.out.println("\n\nGood Bye");
    }
}
