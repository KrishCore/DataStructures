import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DS2_Infix_Postfix_Calculator// <E> implements StackInterface<E>
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an equation in infix form (separating values and operators with spaces): \n");
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
        ArrayList<Double> me = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        String postfixOutput = "";
//        ArrayList <String> ops = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
//            boolean addOp = false;

            if (list[i].equals("("))
                stack.push("(");

            else if (list[i].equals(")"))
                while (stack.pop() != "(") {}

            if (list[i].equals("+") && !stack.isEmpty() && stack.peek() != "(" && (stack.peek() == "*" || stack.peek() == "/" || stack.peek() == "+" || stack.peek() == "-" || stack.peek() == "^")) {
                stack.push("+");
//                ops.add("+");
            }
            else if (list[i].equals("-") && !stack.isEmpty() && stack.peek() != "(" && (stack.peek() == "*" || stack.peek() == "/" || stack.peek() == "+" || stack.peek() == "-" || stack.peek() == "^"))
            {
                stack.push("-");
//                ops.add("-");
            }
//            + - * / ^
//            else if (list[i].equals("+") || list[i].equals("-") || list[i].equals("*") || list[i].equals("/") || list[i].equals("^")) {
////                while (!stack.isEmpty() && stack.peek() != "(" && stack.peek() != "*" && stack.peek() != "/")
////                    stack
//            }
            else if (list[i].equals("*") && !stack.isEmpty() && stack.peek() != "(" && stack.peek() != "^") {
                stack.push("*");
//                ops.add("*");
            }
            else if (list[i].equals("/") && !stack.isEmpty() && stack.peek() != "(" && stack.peek() != "^") {
                stack.push("/");
//                ops.add("/");
            }

            else if (list[i].equals("^") && !stack.isEmpty() && stack.peek() != "(" || stack.peek()== "^") {
                stack.push("^");
            }

//            else if (Double.parseDouble(list[i]) >= 0) {
//                me.add(Double.parseDouble(list[i]));
//                postfixOutput += list[i] + " ";
//            }
//            if (addOp) { // need to add after the second nuber is added
//                postfixOutput += ops.getLast() + " ";
//                addOp = false;
//            }
            System.out.println(stack);

        }

        String[] temp = postfixOutput.split(" ");

//        for (int i = 0; i < me.size(); i++) {
//            double num = me.get(i);
//            char c = (char) num;
//            if (infix.charAt(i) == c){}
//                // needs work
//        }
        while (!stack.isEmpty())
            postfixOutput += stack.pop();
        return stack.toString() + "___" + postfixOutput;      //me.toString();//.substring(0,me.size());
    }
    public static double solvePostfix (String postFix)
    {
//        String[] list = postFix.split(" ");
        double sum = 0;
//        for (int i = 0; i < list.length; i++) {
//            if (list[i].equals("^"))
//                sum += (double) Math.pow(Double.parseDouble(list[i-2]), Double.parseDouble(list[i-1]));
//            if (list[i].equals("*"))
//                sum += (double) Double.parseDouble(list[i-2]) * Double.parseDouble(list[i-1]);
//            if (list[i].equals("/"))
//                sum += (double) Double.parseDouble(list[i-2]) / Double.parseDouble(list[i-1]);
//        }
        return sum;
    }

     // methods if implementing StackInterface
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
