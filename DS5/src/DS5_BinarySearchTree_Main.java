
import java.util.Scanner;

public class DS5_BinarySearchTree_Main {
    public static void main(String[]args)
    {
        Scanner keyboard= new Scanner(System.in);

        int choice;
        DS5_BinarySearchTree<Integer> tree = new DS5_BinarySearchTree<>();

        do
        {
            System.out.print("\n-Menu-\n");
            System.out.println("1. Print");
            System.out.println("2. Size");
            System.out.println("3. Clear");
            System.out.println("4. Empty");
            System.out.println("5. Insert");
            System.out.println("6. Remove");
            System.out.println("7. Contains");
            System.out.println("8. Min Value");
            System.out.println("9. Max Value");
            System.out.println("10. Max Depth");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");
            choice= keyboard.nextInt();

            System.out.println();
            if(choice==1)
            {
                System.out.println("The result of your preorder call is "+tree.preOrder());
                System.out.println("The result of your inorder call is "+tree.inOrder());
                System.out.println("The result of your postorder call is "+tree.postOrder());
            }
            else if(choice==2)
            {
                System.out.println("The result of your size call is "+tree.size());
            }
            else if(choice==3)
            {
                tree.clear();
                System.out.println("clear was called.");
            }
            else if(choice==4)
            {
                System.out.println("The result of your size call is "+tree.isEmpty());
            }
            else if(choice==5)
            {
                System.out.print("Enter the number you want to insert: ");
                Integer number = keyboard.nextInt();
                boolean added = tree.insert(number);
                if(added)
                    System.out.println(number+" was added.");
                else {
                    System.out.println(number+" was not added.");
                }
            }
            else if(choice==6)
            {
                System.out.print("Enter the number you want to remove: ");
                Integer number = keyboard.nextInt();
                System.out.println("The result of your remove call is "+tree.remove(number));

            }
            else if(choice==7)
            {
                System.out.print("Enter the number you to check for: ");
                Integer number = keyboard.nextInt();
                System.out.println("The result of your contains call is "+tree.contains(number));
            }
            else if(choice==8)
            {
                System.out.println("The result of your minValue call is "+tree.minValue());
            }
            else if(choice==9)
            {
                System.out.println("The result of your minValue call is "+tree.maxValue()+".");
            }
            else if(choice==10)
            {
                System.out.println("The result of your maxDepth call is "+tree.maxDepth());
            }
            if(choice==0)
                break;

        }while(true);

        System.out.print("\nGood Bye!!");

    }
}
