import java.util.ArrayList;

public class DS6_PriorityQueue<E extends Comparable> implements DS6_PriorityQueueInterface<E>
{
    ArrayList<E> pq;
    int size;

    public DS6_PriorityQueue()
    {
        pq = new ArrayList<>();
        size = 0;
    }
    
    @Override
    public void offer(E o) {
        pq.add(o);
        size++;
        int index = size-1;
        while (index > 0)
        {
            int pIndex = (index-1)/2;
            if (pq.get(index).compareTo(pq.get(pIndex)) > 0)
            {
                E temp = pq.get(pIndex);
                pq.set(pIndex, pq.get(index));
                pq.set(index, temp);
                index = pIndex;
            } else break;
        }
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;
        E removed = pq.get(0);
        //sdfghkjgftd
        return pq.remove(0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        pq = new ArrayList<>();
        size = 0;
    }

    @Override
    public E get(int x) {
        return pq.get(x);
    }

    @Override
    public E element() {
        if (size == 0)
            return null;
        return pq.get(0);
    }
    
    public String toString()
    {
        String s = "[";
        for (int i = 0; i < size - 1; i++) {
            s += pq.get(i) + ", ";
        }
        return s + pq.get(size-1) + "]";
    }
}
