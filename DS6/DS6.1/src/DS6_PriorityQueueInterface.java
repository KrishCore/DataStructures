
public interface DS6_PriorityQueueInterface <E extends  Comparable>{
    public void offer(E o);
    public E poll();
    public int size();
    public boolean isEmpty();
    public void clear();
    public E get(int x);
    public E element();
}
