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
        DS4_LinkedList_Node<E> rf = first;
        first = rf.getNext();
        size--;
        return rf.getData();
    }

    @Override
    public E removeLast() {
        DS4_LinkedList_Node<E> rl = last;
        DS4_LinkedList_Node<E> cur = first;
        if (size == 1)
        {
            first = last = null;
        }
        else {
            for (int i = 0; i < size; i++) {
                cur = cur.getNext();
            }
            last = cur;
        }
        size--;
        return rl.getData();
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
            DS4_LinkedList_Node<E> n = first.getNext();
            first = node;
            first.setNext(fn);
            fn.setNext(n);
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
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int x) {
        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = first.getNext();
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
        } else if (x == size - 1) {
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
        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = cur.getNext();
        }

        DS4_LinkedList_Node<E> node = cur;
        if (size == 1)
            first = last = null;


        else if (cur.getNext() == null)
        {
            if (x == size-1)
                removeLast();
            else if (x == 0)
                removeFirst();
        }
        else if (cur == first)
            first = cur.getNext();
        else cur.setNext(cur.getNext());
        size--;
        return node.getData();
    }

    @Override
    public E set(int x, E data) {
        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = cur.getNext();
        }
        DS4_LinkedList_Node<E> node = cur;
        cur.setData(data);
        return node.getData();
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
