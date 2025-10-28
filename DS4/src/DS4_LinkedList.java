public class DS4_LinkedList<E> implements DS4_LinkedList_Interface<E>
{

    DS4_LinkedList_Node<E> first;
    DS4_LinkedList_Node<E> last;
    int size;

    public DS4_LinkedList()
    {
        this.first = null;
        this.last = null;
    }
    public DS4_LinkedList(E data)
    {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        this.first = node;
        this.last = node;
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
        this.first = rf.getNext();
        size--;
        return rf.getData();
    }

    @Override
    public E removeLast() {
        DS4_LinkedList_Node<E> rl = last;
        this.last = null;
        size--;
        return rl.getData();
    }


    @Override
    public void addFirst(E data) {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        DS4_LinkedList_Node<E> fn = first;
        this.first = node;
        first.setNext(fn);
        if (last == null)
            last = node;
        size++;
    }

    @Override
    public void addLast(E data) {
        DS4_LinkedList_Node<E> node = new DS4_LinkedList_Node<>(data);
        if (last!=null) {
            DS4_LinkedList_Node<E> ln = last;
            last.setNext(node);
            this.last = ln;
        }
        else if (isEmpty()) {
            first = node;
            last = node;
        }
        else last = new DS4_LinkedList_Node<>(data);
        size++;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
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
            node.setNext(cur);
            first = node;
        }
        else {
            for (int i = 0; i < x-1; i++) {
                cur = cur.getNext();
            }
            node.setNext(cur.getNext());
            cur.setNext(node);
        }
    }

    @Override
    public E remove(int x) {
        DS4_LinkedList_Node<E> cur = first;
        for (int i = 0; i < x; i++) {
            cur = first.getNext();
        }
//        cur = cur.getNext();
        DS4_LinkedList_Node<E> node = cur;
        if (cur.getNext() == null)
        {
            if (node == last)
                last = null;
            else if (node == first) {
                first = null;
                last = null;
            }
        }
        else if (node == first)
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
        cur = cur.getNext();
        DS4_LinkedList_Node<E> node = cur;
        cur.setData(data);
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null && size == 0;
    }

    @Override
    public String toString() {
        DS4_LinkedList_Node<E> cur = first;
        if (size == 1)
            return "[" + first.getData() + "]";
        else if (size == 0)
            return "[]";
        String string = "[";
        for (int i = 0; i < size-1; i++) {
            string += cur.getData() + ", ";
            cur = cur.getNext();
        }
        string += cur.getData();
        return string+"]";
    }
}
