import java.util.Stack;

public class MyStack<String>
{
    static Stack stack;

    public MyStack()
    {

    }

    public static int size()
    {
        return stack.size();
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public void push(String text)
    {
        stack.push(text);
    }

    public String peek()
    {
        return (String) stack.peek();
    }

    public String pop()
    {
        return (String) stack.pop();
    }
}
