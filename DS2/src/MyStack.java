import java.util.ArrayList;

public class MyStack implements StackInterface
{
    public ArrayList<Object> array = new ArrayList<>();

    @Override
    public void push(Object o) {
        array.add(o);
    }

    @Override
    public Object peek() {
        return array.get(array.size()-1);
    }

    @Override
    public Object pop() {
        return array.remove(array.size()-1);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
