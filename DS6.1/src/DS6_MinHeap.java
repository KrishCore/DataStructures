import java.util.ArrayList;

public class DS6_MinHeap<E extends Comparable> implements DS6_HeapInterface<E>
{
    ArrayList<E> heap;
    int size = 0;

    @Override
    public void insert(E item) {

    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        heap.clear();
        size = 0;
    }
}
