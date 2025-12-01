public class DS5_BinarySearchTree<E extends Comparable> implements DS5_BinarySearchTree_Interface<E>
{
    DS5_BinarySearchTree_Node<E> root;
    int size;

    public DS5_BinarySearchTree()
    {
        root = null;
        size = 0;
    }

    public DS5_BinarySearchTree(E data)
    {
        root = new DS5_BinarySearchTree_Node<>(data);
        size = 1;
    }

    @Override
    public DS5_BinarySearchTree_Node<E> getRoot() {
        return root;
    }

    @Override
    public String preOrder()
    {
        if(root == null)
            return "[]";
        return "["+preOrderHelper(root).substring(2)+"]";
    }

    public String preOrderHelper(DS5_BinarySearchTree_Node<E> temp)
    {
        if(temp == null)
            return "";
        else return ", " + temp.getData() + preOrderHelper(temp.getLeft()) + preOrderHelper(temp.getRight());
    }

    @Override
    public String inOrder() {
        if (root == null)
            return "[]";
        return "[" + inOrderHelper(root).substring(2) + "]";
    }
    public String inOrderHelper(DS5_BinarySearchTree_Node<E> temp)
    {
        if(temp == null)
            return "";
        else return inOrderHelper(temp.getLeft()) + ", " + temp.getData() + inOrderHelper(temp.getRight());
    }

    @Override
    public String postOrder() {
        if (root == null)
            return "[]";
        return "[" + postOrderHelper(root).substring(2) + "]";
    }
    public String postOrderHelper(DS5_BinarySearchTree_Node<E> temp)
    {
        if(temp == null)
            return "";
        return postOrderHelper(temp.getLeft()) + postOrderHelper(temp.getRight()) + ", " + temp.getData();
    }

    @Override
    public E minValue() {
        if (root == null)
            return null;
        DS5_BinarySearchTree_Node<E> cur = root;
        while (cur.getLeft() != null)
                cur = cur.getLeft();
        return cur.getData();
    }

    @Override
    public E maxValue() {
        if (root == null)
            return null;
        DS5_BinarySearchTree_Node<E> cur = root;
        while (cur.getRight() != null)
            cur = cur.getRight();
        return cur.getData();
    }

    @Override
    public int nodeDepth(E value) {
        return nodeDepthHelper(root, value, 0);
    }

    private int nodeDepthHelper(DS5_BinarySearchTree_Node<E> node, E value, int d) {
        if (node == null)
            return -1;
        int compare = node.getData().compareTo(value);
        if (compare == 0)
            return d;
        if (compare > 0)
            return nodeDepthHelper(node.getLeft(), value, d+1);
        return nodeDepthHelper(node.getRight(), value, d+1); // compare < 0
    }

    @Override
    public int height() { // is this done? most likely but check
        if (root == null)
            return 0;
        return maxDepth()+1;
    }

    @Override
    public int maxDepth() {
        if (root == null)
            return -1;
        return Math.max(maxDepthHelper(root), 0);
    }

    private int maxDepthHelper(DS5_BinarySearchTree_Node<E> node) {
        if (node == null)
            return -1;
        return 1 + Math.max(maxDepthHelper(node.getRight()), maxDepthHelper(node.getLeft()));
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return sizeHelper(root);
    }

    public int sizeHelper(DS5_BinarySearchTree_Node<E> t)
    {
        if(t==null)
            return 0;
        return 1 + sizeHelper(t.getLeft()) + sizeHelper(t.getRight());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E data) {
        DS5_BinarySearchTree_Node<E> cur = root;
        while (cur != null) {
            int compare = cur.getData().compareTo(data);
            if (compare == 0)
                return true;
            if (compare > 0)
                cur = cur. getLeft();
            else cur = cur.getRight(); // compare < 0
        }
        return false;
    }

    @Override
    public boolean insert(E data) { //needs work
        if (data == null)
            return false;
        if (contains(data))
            return false;
        if (root == null)
        {
            root = new DS5_BinarySearchTree_Node<>(data);
            size = 1;
            return true;
        }
        DS5_BinarySearchTree_Node<E> cur =root;
        while (true)
        {
            int compare = cur.getData().compareTo(data);
            if (compare == 0)
                return false;
            if (compare > 0)
            {
                if (cur.getLeft() == null) {
                    cur.setLeft(new DS5_BinarySearchTree_Node<>(data));
                    size++;
                    return true;
                }
                cur = cur.getLeft();
            }
            if (compare < 0)
            {
                if (cur.getRight() == null){
                    cur.setRight(new DS5_BinarySearchTree_Node<>(data));
                    size++;
                    return true;
                }
                cur = cur.getRight();
            }
        }
    }

    @Override
    public boolean remove(E data) {
        if (data == null || !contains(data))
            return false;
        root = removeHelper(root, data);
        size--;
        return true;
    }

    private DS5_BinarySearchTree_Node<E> removeHelper(DS5_BinarySearchTree_Node<E> node, E data) {
        if (node == null)
            return null;
        int compare = node.getData().compareTo(data);

        if (compare > 0)
            node.setLeft(removeHelper(node.getLeft(), data));

        else if (compare < 0)
            node.setRight(removeHelper(node.getRight(), data));
        else { // compare == 0
            if (node.getLeft() == null && node.getRight() == null)
                return null;
            if (node.getLeft() == null)
                return node.getRight();
            if (node.getRight() == null)
                return node.getLeft();

            DS5_BinarySearchTree_Node<E> successor = getMinNode(node.getRight());
            node.setData(successor.getData());
            node.setRight(removeHelper(node.getRight(), successor.getData()));
        }
        return node;
    }

    public DS5_BinarySearchTree_Node<E> getMinNode(DS5_BinarySearchTree_Node<E> node) {
        while (node != null && node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void traversal(DS5_BinarySearchTree_Node<E> t)
    {
        if(t==null)
            return;
        traversal(t.getLeft());
        traversal(t.getRight());
    }
}
