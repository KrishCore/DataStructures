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
        //minHeap
        size++;
        pq.add(o);
        if (!isEmpty())
        {
            int index = size-1;
            int pIndex = (index-1)/2;
            while (pq.get(index).compareTo(pq.get(pIndex)) > 0)
            {
                if (pq.get(index).compareTo(pq.get(pIndex)) < 0)
                {
                    //swap
                    E pInd = pq.get(pIndex);
                    E ind = pq.get(index);
                    pq.set(pIndex, ind);
                    pq.set(index, pInd);

                    index = pIndex;
                } else break;

            }
        }

    }

    @Override
    public E poll() { // needs work
        return null;
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
        pq.clear();
        size = 0;
    }

    @Override
    public E get(int x) {
        return pq.get(x);
    }

    @Override
    public E element() {
        if (isEmpty())
            return null;
        else return pq.get(0);
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size - 1; i++) {
            s += pq.get(i) + ", ";
        }
        return s + pq.getLast() + "]";
    }
}
