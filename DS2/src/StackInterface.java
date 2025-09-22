
public interface StackInterface<E>
{
    public void push(E o);
    public E peek();
    public E pop();
    public int size();
    public boolean isEmpty();
    public void clear();
}
