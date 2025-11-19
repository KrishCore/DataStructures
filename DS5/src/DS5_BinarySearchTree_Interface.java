
public interface DS5_BinarySearchTree_Interface<E extends Comparable>
{
    public DS5_BinarySearchTree_Node<E> getRoot();
    public String preOrder();
    public String inOrder();
    public String postOrder();
    public E minValue();
    public E maxValue();
    public int nodeDepth(E value);
    public int height();
    public int maxDepth();
    public void clear();
    public int size();
    public boolean isEmpty();
    public boolean contains(E data);

    boolean contains(E data, DS5_BinarySearchTree_Node<E> tree);

    public boolean insert(E data);
    public boolean remove(E data);
}
