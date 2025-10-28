
public class DS4_Doubly_Circular_LinkedList_Node<E>
{
    private E data;
    private DS4_Doubly_Circular_LinkedList_Node<E> next;
    private DS4_Doubly_Circular_LinkedList_Node<E> prev;

    public DS4_Doubly_Circular_LinkedList_Node(E data)
    {
        this.data 	= data;
        next		= null;
        prev		= null;
    }

    public E getData()
    { return data; }

    public void setData(E data)
    { this.data = data; }

    public DS4_Doubly_Circular_LinkedList_Node<E> getNext()
    { return next; }

    public void setNext(DS4_Doubly_Circular_LinkedList_Node<E> next)
    { this.next = next; }

    public DS4_Doubly_Circular_LinkedList_Node<E> getPrev()
    { return prev; }

    public void setPrev(DS4_Doubly_Circular_LinkedList_Node<E> prev)
    { this.prev = prev; }
}
