import java.util.ArrayList;
import java.util.Scanner;

public class DS2_Infix_Postfix_Calculator// <E> implements StackInterface<E>
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an equation in infix form (separating values and operators with spaces): ");
        String equation = scan.nextLine();
        String itp = infixToPostfix(equation);
        System.out.println("..equation: " + equation);
        System.out.println("Postfix Form: " + itp);
        double sp = solvePostfix(itp);
        System.out.println("Result: " + sp);
    }
    public static String infixToPostfix (String infix)
    {
        String[] list = infix.split(" ");
        ArrayList<Integer> me = new ArrayList<Integer>();
        String modEqu = list[0] + " ";
        ArrayList <String> ops = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals("+")) {
                ops.add("+");
            }
            else if (list[i].equals("-")) {
                ops.add("-");
            }
            else if (list[i].equals("*")) {
                ops.add("*");
            }
            else if (list[i].equals("/")) {
                ops.add("/");
            }
            else if (list[i].equals("(")) {
                continue;
            }
            else if (list[i].equals(")")) {
                continue;
            }
            else if (Integer.parseInt(list[i]) >= 0) {
                me.add(Integer.parseInt(list[i]));
                modEqu += list[i] + "";
            }
        }
        String temp = modEqu;
        for (int i = 0; i < me.size(); i++) {
            int num = me.get(i);
            char c = (char) num;
            if (
                    infix.charAt(i) == c){}
                // needs work
        }
        return modEqu;      //me.toString();//.substring(0,me.size());
    }
    public static double solvePostfix (String postFix)
    {
        String[] list = postFix.split(" ");

        return 1.0;
    }

//     methods if implementing StackInterface
//    @Override
//    public void push(E o) {
//
//    }
//
//    @Override
//    public E peek() {
//        return null;
//    }
//
//    @Override
//    public E pop() {
//        return null;
//    }
//
//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }

}
