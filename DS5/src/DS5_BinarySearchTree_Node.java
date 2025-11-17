
public class DS5_BinarySearchTree_Node<E extends Comparable>
{
    private E data;
    private DS5_BinarySearchTree_Node<E> left;
    private DS5_BinarySearchTree_Node<E> right;

    public DS5_BinarySearchTree_Node(E data)
    {
        this.data 		= data;
        left = right	= null;
    }

    public E getData()
    { return data; }

    public void setData(E data)
    { this.data = data; }

    public DS5_BinarySearchTree_Node<E> getLeft()
    { return left; }

    public void setLeft(DS5_BinarySearchTree_Node<E> left)
    { this.left = left; }

    public DS5_BinarySearchTree_Node<E> getRight()
    { return right; }

    public void setRight(DS5_BinarySearchTree_Node<E> right)
    { this.right = right; }
}
