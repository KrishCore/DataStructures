public class DS5_BinarySearchTree<E extends Comparable> implements DS5_BinarySearchTree_Interface<E>
{
    DS5_BinarySearchTree_Node<E> root;
    int size;
    String arr;

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
        if(root==null)
            return "[]";
        return "["+preOrderHelper(root).substring(2)+"]";
    }

    public String preOrderHelper(DS5_BinarySearchTree_Node<E> temp)
    {
        if(temp==null)
            return "";
        else
            return ", "+temp.getData() +
                    preOrderHelper(temp.getLeft()) +
                    preOrderHelper(temp.getRight());
    }

    @Override
    public String inOrder() {
        if (size == 0)
            return "[]";
        else if (size == 1)
            return "[" + root.getData() + "]";
        return "";
    }

    @Override
    public String postOrder() {
        if (size == 0)
            return "[]";
        else if (size == 1)
            return "[" + root.getData() + "]";
        String[] string = traversal(root).split(" ");
        return "[" + string + "]";
    }

    @Override
    public E minValue() {
        E max = root.getData();
        DS5_BinarySearchTree_Node<E> cur = root;
        boolean found = false;
        while (!found)
        {
            if (cur.getRight() != null)
            {}
            else if (cur.getLeft() != null)
                cur = cur.getLeft();
        }
        return max;
    }

    @Override
    public E maxValue() {
        return null;
    }

    @Override
    public int nodeDepth(E value) {
        if (!contains(value))
            return -1;
        else { // code to check where is actually is
            if (root.getData() == value)
                return 0;
            return 1;
        }
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int maxDepth() {
        if (size == 0)
            return -1;
        else if (size == 1)
            return 0;
        else if (size == 2)
            return 1;
        return 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E data) {
        return contains(data, root);
    }

    @Override
    public boolean contains(E data, DS5_BinarySearchTree_Node<E> cur) {
        //this will be a helping recursive method

        if (cur.getData() == data)
            return true;
        else if (cur.getLeft() != null) {
            return contains(data, cur.getLeft());
        }
        else if (cur.getRight() != null) {
            return contains(data, cur.getRight());
        }

        DS5_BinarySearchTree_Node<E> node = new DS5_BinarySearchTree_Node<>(data);
        boolean bool = false;
        if (root == null || data == null)
            return false;
        if (root.getData().equals(data))
            return true;
        DS5_BinarySearchTree_Node<E> cur1 = root;
        while (!bool) {
            if (cur1.getData().compareTo(data) < 0) {
                cur1 = cur1.getRight();
                if (cur1 == null)
                    return false;
                if (node.getData().equals(cur1.getData()))
                    return true;
            } else if (cur1.getData().compareTo(data) > 0) {
                cur1 = cur1.getLeft();
                if (cur1 == null)
                    return false;
                if (node.getData().equals(cur1.getData()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(E data) { //needs work
        DS5_BinarySearchTree_Node<E> node = new DS5_BinarySearchTree_Node<>(data);
        boolean inserted = false;
        if (root == null) {
            root = new DS5_BinarySearchTree_Node<>(data);
            size++;
            return true;
        }
        if (data == null)
            return false;
        DS5_BinarySearchTree_Node<E> cur = root;
        while (!inserted)
        {
            if (cur.getData().compareTo(data) < 0) {
                if (cur.getRight() == null) {
                    cur.setRight(node);
                    size++;
                    inserted = true;
                }
                else cur = cur.getRight();
            }
            else if (cur.getData().compareTo(data) > 0) {
                if (cur.getLeft() == null) {
                    cur.setLeft(node);
                    size++;
                    inserted = true;
                }
                else cur = cur.getLeft();
            }
            else return false;
        }
        return inserted;
    }

    @Override
    public boolean remove(E data) {
        if (!contains(data))
            return false;
        boolean removed = false;
        DS5_BinarySearchTree_Node<E> cur = root;
        while (!removed)
        {
            if (cur.getData().compareTo(data) < 0) {
                if (cur.getRight() == null) {
                    cur.setRight(null);
                    size++;
                    removed = true;
                }
                else cur = cur.getRight();
            }
            else if (cur.getData().compareTo(data) > 0) {
                if (cur.getLeft() == null) {
                    cur.setLeft(null);
                    size++;
                    removed = true;
                }
                else cur = cur.getLeft();
            }
            else return false;
        }

        return false;
    }

    public String traversal(DS5_BinarySearchTree_Node<E> t)
    {
        if(t==null)
            return "";
        arr += traversal(t.getLeft()) + " ";
        arr += traversal(t.getRight()) + " ";
        return arr;
    }


}
