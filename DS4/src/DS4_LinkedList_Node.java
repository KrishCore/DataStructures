
public class DS4_LinkedList_Node<E> {
    private E data;
    private DS4_LinkedList_Node<E> next;

    public DS4_LinkedList_Node(E data)
    {
        this.data 	= data;
        next		= null;
    }

    public E getData()
    { return data; }

    public void setData(E data)
    { this.data = data; }

    public DS4_LinkedList_Node<E> getNext()
    { return next; }

    public void setNext(DS4_LinkedList_Node<E> next)
    { this.next = next; }
}
