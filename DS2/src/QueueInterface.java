
public interface QueueInterface<E>
{
    public void offer(E o);
    public E element();
    public E poll();
    public int size();
    public boolean isEmpty();
    public void clear();
}
