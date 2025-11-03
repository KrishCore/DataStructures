public class DS4_LinkedList<E> implements DS4_LinkedList_Interface<E>
{

    DS4_LinkedList_Node<E> first;
    DS4_LinkedList_Node<E> last;
    int size;

    public DS4_LinkedList()
    {
        first = last = null;
    }
    public DS4_LinkedList(E data)
    {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        first = last = node;
        size = 1;
    }

    @Override
    public DS4_LinkedList_Node<E> getFirstNode() {
        return first;
    }

    @Override
    public DS4_LinkedList_Node<E> getLastNode() {
        return last;
    }

    @Override
    public E getFirst() {
        return first.getData();
    }

    @Override
    public E getLast() {
        return last.getData();
    }

    @Override
    public E removeFirst() {
        if (isEmpty())
            return null;

        E data = first.getData();
        first = first.getNext();
        size--;
        if (size == 0)
            first = last = null;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;

        DS4_LinkedList_Node<E> rl = last;
        DS4_LinkedList_Node<E> cur = first;
        if (size == 1)
        {
            E data = last.getData();
            clear();
            return data;
        }
        else {
            while (cur.getNext() != last)
                cur = cur.getNext();
            E data = last.getData();
            cur.setNext(null);
            last = cur;
            size--;
            return data;
        }
    }


    @Override
    public void addFirst(E data) {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        if (isEmpty())
        {
            first = last = node;
        }
        else {
            DS4_LinkedList_Node<E> fn = first;
//            DS4_LinkedList_Node<E> n = first.getNext();
            first = node;
            first.setNext(fn);
//            fn.setNext(n);
            if (last == first)
                last = node;
        }
        size++;
    }

    @Override
    public void addLast(E data) {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        if (isEmpty())
            first = node;
        else last.setNext(node);
        last = node;
        size++;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int x) {
        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = cur.getNext();
        }
        return cur.getData();
    }

    @Override
    public void add(int x, E data) {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        DS4_LinkedList_Node<E> cur = first;
        if (x == 0) {
            addFirst(data);
//            node.setNext(cur);
//            first = node;
        } else if (x == size) {
            addLast(data);
        } else {
            for (int i = 0; i < x-1; i++) {
                cur = cur.getNext();
            }
            node.setNext(cur.getNext());
            cur.setNext(node);
            size++;
        }
    }

    @Override
    public E remove(int x) {
        if (x == 0) return removeFirst();
        if (x == size - 1) return removeLast();

        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x - 1; i++) {
            cur = cur.getNext();
        }
        E data = cur.getNext().getData();
        cur.setNext(cur.getNext().getNext());
        size--;
        return data;
    }

    @Override
    public E set(int x, E data) {
        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = cur.getNext();
        }
        E old = cur.getData();
        cur.setData(data);
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        DS4_LinkedList_Node<E> cur = first;
        if (size == 0)
            return "[]";
        else if (size == 1)
            return "[" + first.getData() + "]";
        String string = "[";
        for (int i = 0; i < size-1; i++) {
            string += cur.getData() + ", ";
            cur = cur.getNext();
        }
        string += cur.getData();
        return string+"]";
    }
}
