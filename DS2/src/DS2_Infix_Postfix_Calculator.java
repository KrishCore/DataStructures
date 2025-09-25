import java.util.Scanner;
import java.util.Stack;

public class DS2_Infix_Postfix_Calculator// <E> implements StackInterface<E>
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an equation in infix form (separating values and operators with spaces): \n");
        String equation = scan.nextLine();
        String infixToPostfix = infixToPostfix(equation);
        System.out.println("..equation: " + equation);
        System.out.println("Postfix Form: " + infixToPostfix);
        double solvePostfix = solvePostfix(infixToPostfix);
        System.out.println("Result: " + solvePostfix);
    }
    public static String infixToPostfix(String infix) {
        String[] list = infix.split(" ");
        Stack<String> stack = new Stack<>();
        String postfixOutput = "";

        for (int i = 0; i < list.length; i++) {
            String current = list[i];

            // Check if current is a number
            boolean isNumber = true;
            try {
                Double.parseDouble(current);
            } catch (Exception e) {
                isNumber = false;
            }

            if (isNumber)
                postfixOutput += current + " "; // ads to stack if number
            else if (current.equals("(")) // adds  "("
                stack.push(current);
            else if (current.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) //looks for ")"
                    postfixOutput += stack.pop() + " "; // adds everything up til ")" to String and removes it from stack
                if (!stack.isEmpty())
                    stack.pop(); //remove "(" if empty cuz that's the only thing there
            } else {
                while (!stack.isEmpty()) {
                    String top = stack.peek();

                    if (top.equals("("))
                        break;

                    // precedence senarios:
                    // if current operator is + or - and passes the precedence test
                    if ((current.equals("+") || current.equals("-")) && (top.equals("+") || top.equals("-") || top.equals("*") || top.equals("/") || top.equals("^")))
                        postfixOutput += stack.pop() + " ";

                    // if current operator is * or / and passes the precedence test
                    else if ((current.equals("*") || current.equals("/")) && (top.equals("*") || top.equals("/") || top.equals("^")))
                        postfixOutput += stack.pop() + " ";

                    // if current operator is ^ and passes the precedence test
                    else if (current.equals("^") && top.equals("^"))
                        postfixOutput += stack.pop() + " ";

                    else break;
                }

                stack.push(current); //pushes the operator after pushing the number after it
            }
        }
        while (!stack.isEmpty()) {
            postfixOutput += stack.pop() + " ";
        }

        return postfixOutput.substring(0, postfixOutput.length() - 1);
    }

    public static double solvePostfix (String postFix)
    {
        String[] list = postFix.split(" ");
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < list.length; i++) {
            String current = list[i];

            // checks if current is a number
            boolean isNum = true;
            try {
                Double.parseDouble(current);
            } catch (Exception e) {
                isNum = false;
            }

            if (isNum)
                stack.push(Double.parseDouble(current));
            else
            {
                double num2 = stack.pop(); //second number, last number pushed
                double num1 = stack.pop(); // first number, number before num2
                double result = 0;

                if (current.equals("+"))
                    result = num2 + num1;
                if (current.equals("-"))
                    result = num2 - num1;
                if (current.equals("*"))
                    result = num2 * num1;
                if (current.equals("/"))
                    result = num2 / num1;
                if (current.equals("^"))
                    result = Math.pow(num2, num1);

                stack.push(result); // puts it on the top of the stack: becomes num2
            }
        }
        double sum = 0;
        while (!stack.isEmpty())
            sum += stack.pop();
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
