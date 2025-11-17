public class DS5_BinarySearchTree<E extends Comparable> implements DS5_BinarySearchTree_Interface<E>
{
    DS5_BinarySearchTree_Node<E> root;
    int size = 0;

    @Override
    public DS5_BinarySearchTree_Node<E> getRoot() {
        return null;
    }

    @Override
    public String preOrder() {
        return "";
    }

    @Override
    public String inOrder() {
        return "";
    }

    @Override
    public String postOrder() {
        return "";
    }

    @Override
    public E minValue() {
        return null;
    }

    @Override
    public E maxValue() {
        return null;
    }

    @Override
    public int nodeDepth(E value) {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int maxDepth() {
        return 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(E data) {
        return false;
    }

    @Override
    public boolean insert(E data) { //needs work
        DS5_BinarySearchTree_Node<E> node = new DS5_BinarySearchTree_Node<>(data);
        boolean inserted = false;
        if (data == null || data == root.getData())
            return false;
        DS5_BinarySearchTree_Node<E> cur = root;
        while (!inserted)
        {
            if (data.compareTo(cur.getData())>0) {
                if (cur.getRight() == null)
                    cur.setRight(node);
                else cur = cur.getRight();
            }
            else if (data.compareTo(cur.getData())<0) {
                if (cur.getLeft() == null)
                    cur.setLeft(node);
                else cur = cur.getLeft();
            }

        }
        return false;
    }

    @Override
    public boolean remove(E data) {
        return false;
    }

}
