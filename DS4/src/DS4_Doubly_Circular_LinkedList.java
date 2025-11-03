public class DS4_Doubly_Circular_LinkedList<E> implements DS4_Doubly_Circular_LinkedList_Interface<E>
{
    int size;
    DS4_Doubly_Circular_LinkedList_Node<E> first;
    DS4_Doubly_Circular_LinkedList_Node<E> last;
    public DS4_Doubly_Circular_LinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }
    @Override
    public DS4_Doubly_Circular_LinkedList_Node<E> getFirstNode() {
        return first;
    }

    @Override
    public DS4_Doubly_Circular_LinkedList_Node<E> getLastNode() {
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
        DS4_Doubly_Circular_LinkedList_Node<E> rf = first;
        first = rf.getNext();
        rf.setPrev(last);
        last.setNext(rf);
        size--;
        return rf.getData();
    }

    @Override
    public E removeLast() {
        DS4_Doubly_Circular_LinkedList_Node<E> rl = last;
        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        if (size == 1)
        {
            first = last = null;
        }
        else {
            for (int i = 0; i < size; i++) {
                cur = cur.getNext();
            }
            last = cur;
            last.setNext(first);
            first.setPrev(last);
        }
        size--;
        return rl.getData();
    }

    @Override
    public void addFirst(E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> node = new DS4_Doubly_Circular_LinkedList_Node<>(data);
        if (isEmpty())
        {
            first = last = node;
        }
        else {
            DS4_Doubly_Circular_LinkedList_Node<E> fn = first;
            first = node;
            first.setNext(fn);
            first.setPrev(last);
            last.setNext(first);
            if (last == first)
                last = node;
        }
        size++;
    }

    @Override
    public void addLast(E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> node = new DS4_Doubly_Circular_LinkedList_Node<>(data);
        if (isEmpty()) {
            first = node;
            last = node;
        }
        else {
            last.setNext(node);
            last = node;
            last.setNext(first);
            first.setPrev(last);
        }

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
        DS4_Doubly_Circular_LinkedList_Node<E> node = first;
        for (int i = 0; i < x; i++) {
            node = node.getNext();
        }
        return node.getData();
    }

    @Override
    public void add(int x, E data) {

    }

    @Override
    public E remove(int x) {
        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = cur.getNext();
        }

        DS4_Doubly_Circular_LinkedList_Node<E> node = cur;
        if (size == 1)
            first = last = null;


        else if (cur.getNext() == null)
        {
            if (x == size-1) {
                removeLast();
            }
            else if (x == 0) {
                removeFirst();
            }
        }
        else if (cur == first) {
            first = cur.getNext();
        }
        else {
            cur.setNext(cur.getNext());
        }
        size--;
        return node.getData();
    }

    @Override
    public E set(int x, E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> node = first;
        for (int i = 0; i < x; i++) {
            node = node.getNext();
        }
        DS4_Doubly_Circular_LinkedList_Node<E> ph = node;
        node.setData(data);
        return ph.getData();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String backwardsToString() {DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        if (size == 0)
            return "[]";
        else if (size == 1)
            return "[" + first.getData() + "]";
        String string = "[";
        for (int i = size-1; i >= 0 ; i--) {
            string += cur.getData() + ", ";
            cur = cur.getNext();
        }
//        string += cur.getData();
        string = string.substring(0, string.length()-1);
//        string = string.substring(0, string.length()-1);
        return string+"]";
    }

//    @Override
    public String toString() {
        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        if (size == 0)
            return "[]";
        else if (size == 1)
            return "[" + first.getData() + "]";
        String string = "[";
        for (int i = size-1; i >= 0 ; i--) {
            string += cur.getData() + ", ";
            cur = cur.getNext();
        }
//        string += cur.getData();
        string = string.substring(0, string.length()-1);
//        string = string.substring(0, string.length()-1);
        return string+"]";
    }
}
