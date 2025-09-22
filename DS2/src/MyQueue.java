import java.util.ArrayList;

public class MyQueue implements QueueInterface
{
    private ArrayList<Object> array = new ArrayList<>();

    @Override
    public void offer(Object o) {
        array.add(o);
    }

    @Override
    public Object element() {
        return array.get(0);
    }

    @Override
    public Object poll() {
        return array.remove(0);
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
    public String toString()
    {
        return array.toString();
    }
}
