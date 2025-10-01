import java.util.ArrayList;
import java.util.Iterator;

public class DS3_Set<E> implements SetInterface<E>
{
    private ArrayList array = new ArrayList<>();

    @Override
    public boolean add(E o) {
        if (!array.contains(o))
            return array.add(o);
        return false;
    }

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public boolean contains(E o) {
        return array.contains(o);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

    @Override
    public boolean remove(E o) {
        if (array.contains(o))
        {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).equals(o))
                    return array.remove(o);
            }
        }
        return false;
    }

    @Override
    public int size() {
        return array.size();
    }
}
