import java.util.ArrayList;

public class DS6_MaxHeap<E extends Comparable> implements DS6_HeapInterface<E>
{
    ArrayList<E> heap;
    int size;

    public DS6_MaxHeap()
    {
        heap = new ArrayList<>();
        size = 0;
    }

    @Override
    public void insert(E item) {
        heap.add(item);
        size++;
        // compare with parents
        int index = size-1;
        while (index > 0)
        {
            int pIndex = (index-1)/2;
            if (heap.get(index).compareTo(heap.get(pIndex)) > 0)
            {
                E temp = heap.get(pIndex);
                heap.set(pIndex, heap.get(index));
                heap.set(index, temp);
                index = pIndex;
            } else break;
        }
    }

    @Override
    public E remove() {
        if (size == 0)
            return null;

        E removed = heap.get(0);
        E last = heap.get(size-1);
        heap.set(0, last);
        heap.remove(size-1);
        size--;

        if (size == 0)
            return removed;

        // compare with children
        int index = 0;
        while (true)
        {
            int left = index*2+1;
            int right = left+1; //index*2+2
            int largest = index;

            if (left < size && heap.get(left).compareTo(heap.get(largest)) > 0)
                largest = left;
            if (right < size && heap.get(right).compareTo(heap.get(largest)) > 0)
                largest = right;
            if (largest == index)
                break;

            E temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);

            index = largest;
        }
        return removed;
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
        heap = new ArrayList<>();
        size = 0;
    }

    public String toString()
    {
        if (isEmpty())
            return "[]";
        String s = "[";
        for (int i = 0; i < size-1; i++) {
            s += heap.get(i) + ", ";
        }
        return s + heap.get(size-1) + "]";
    }
}
