
public interface DS6_HeapInterface<E extends Comparable>
{
    public void insert(E item);
    public E remove();
    public boolean isEmpty();
    public int size();
    public void clear();
}
