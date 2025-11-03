public class DS4_Doubly_Circular_LinkedList<E> implements DS4_Doubly_Circular_LinkedList_Interface<E> {
    int size;
    DS4_Doubly_Circular_LinkedList_Node<E> first;
    DS4_Doubly_Circular_LinkedList_Node<E> last;

    public DS4_Doubly_Circular_LinkedList() {
        first = null;
        last = null;
        size = 0;
    }
    public DS4_Doubly_Circular_LinkedList(E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> node = new DS4_Doubly_Circular_LinkedList_Node<>(data);
        first = last = node;
        node.setNext(node);
        node.setPrev(node);
        size = 1;
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
        if (size == 0) {
            return null;
        }
        E data = first.getData();
        if (size == 1) {
            clear();
            return data;
        }

        first = first.getNext();
        first.setPrev(last);
        last.setNext(first);
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;
        E data = last.getData();
        if (size == 1)
            clear();
        else {
            last = last.getPrev();
            last.setNext(first);
            first.setPrev(last);
            size--;
        }
        return data;
    }

    @Override
    public void addFirst(E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> node = new DS4_Doubly_Circular_LinkedList_Node<>(data);
        if (isEmpty()) {
            first = last = node;
            first.setNext(first);
            first.setPrev(last);
        } else {
            node.setNext(first);
            node.setPrev(last);
            first.setPrev(node);
            last.setNext(node);
            first = node;
        }
        size++;
    }

    @Override
    public void addLast(E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> node = new DS4_Doubly_Circular_LinkedList_Node<>(data);
        if (isEmpty()) {
            first = last = node;
            node.setNext(node);
            node.setPrev(node);
        } else {
            node.setPrev(last);
            node.setNext(first);
            last.setNext(node);
            first.setPrev(node);
            last = node;
        }

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
        DS4_Doubly_Circular_LinkedList_Node<E> node = first;
        for (int i = 0; i < x; i++) {
            node = node.getNext();
        }
        return node.getData();
    }

    @Override
    public void add(int x, E data) {
        if (x == 0)
            addFirst(data);
        else if (x == size)
            addLast(data);
        else {
            DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
            for (int i = 0; i < x; i++) {
                cur = cur.getNext();
            }
            DS4_Doubly_Circular_LinkedList_Node<E> node = new DS4_Doubly_Circular_LinkedList_Node<>(data);
            DS4_Doubly_Circular_LinkedList_Node<E> prev = cur.getPrev();

            node.setNext(cur);
            node.setPrev(prev);
            prev.setNext(node);
            cur.setPrev(node);

            size++;
        }
    }

    @Override
    public E remove(int x) {

        if (x == 0)
            return removeFirst();
        else if (x == size - 1)
            return removeLast();

        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = cur.getNext();
        }
        DS4_Doubly_Circular_LinkedList_Node<E> prev = cur.getPrev();
        DS4_Doubly_Circular_LinkedList_Node<E> next = cur.getNext();

        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return cur.getData();
    }

    @Override
    public E set(int x, E data) {
        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
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
    public String backwardsToString() {
        if (isEmpty())
            return "[]";
        String string = "[";
        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        for (int i = 0; i < size; i++) {
            string += cur.getData();
            if (i < size-1)
                string += ", ";
            cur = cur.getNext();
        }
        return string + "]";
    }

    //    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        String string = "[";
        DS4_Doubly_Circular_LinkedList_Node<E> cur = first;
        for (int i = 0; i < size; i++) {
            string += cur.getData();
            if (i < size-1)
                string += ", ";
            cur = cur.getNext();
        }
        return string + "]";
    }
}

