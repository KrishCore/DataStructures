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
        {
        if (size == 0)
            return "[]";
        if (size == 1)
            return "[" + root.getData() + "]";
        if (size == 2)
            if (root.getLeft() != null)
                return "[" + root.getLeft().getData() + ", " + root.getData() + "]";
            else return "[" + root.getData() + ", " + root.getRight().getData() + "]";
        if (size == 3)
            return "[" + root.getLeft().getData() + ", " + root.getData() + ", " + root.getRight().getData() + "]"; } // if size is 0, 1, 2, or 3
        return "inOrder error";
    }

    @Override
    public String postOrder() {
        if (size == 0)
            return "[]";
        else if (size == 1)
            return "[" + root.getData() + "]";
        System.out.println(traversal(root));
        String[] string = traversal(root).split(" ");
        return "[" + string + "]";
    }

    @Override
    public E minValue() {
        DS5_BinarySearchTree_Node<E> cur = root;
        boolean found = false;
        while (!found)
        {
            if (cur.getRight() == null)
            {
                if (cur.getLeft() != null)
                    cur = cur.getLeft();
                else found = true;
            }
            else if (cur.getLeft() != null)
                cur = cur.getLeft();
            else found = true;
        }
        return cur.getData();
    }

    @Override
    public E maxValue() {
        DS5_BinarySearchTree_Node<E> cur = root;
        boolean found = false;
        while (!found)
        {
            if (cur.getLeft() == null)
            {
                if (cur.getRight() != null)
                    cur = cur.getRight();
                else found = true;
            }
            else if (cur.getRight() != null)
                cur = cur.getRight();
            else found = true;
        }
        return cur.getData();
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
    public int height() { // is this done? most likely but check
        if (size == 0)
            return 0;
        if (size == 1)
            return 1;
        if (size == 2)
            return 2;
        return maxDepth()-1;
    }

    @Override
    public int maxDepth() {
        if (size == 0)
            return -1;
        else if (size == 1)
            return 0;
        else if (size == 2)
            return 1;
        // needs work
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
        DS5_BinarySearchTree_Node<E> cur = root;
        DS5_BinarySearchTree_Node<E> temp = cur;
        if (root == null)
            return false;
        if (cur.getData() == data)
            return true;

        DS5_BinarySearchTree_Node<E> node = new DS5_BinarySearchTree_Node<>(data);
        boolean bool = false;
        {
        if (root == null || data == null)
            return false;
        if (root.getData().equals(data))
            return true;
    }
        while (!bool) {
            if (cur.getData().compareTo(data) < 0) {
                cur = cur.getRight();
                if (cur == null)
                    return false;
                if (node.getData().equals(cur.getData()))
                    return true;
            } else if (cur.getData().compareTo(data) > 0) {
                cur = cur.getLeft();
                if (cur == null)
                    return false;
                if (node.getData().equals(cur.getData()))
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
        if (data == null || !contains(data))
            return false;
        if (root.getData() == data) {
            root = null;
            size = 0;
            return true;
        }

        boolean removed = false;
        DS5_BinarySearchTree_Node<E> cur = root;
        while (!removed)
        {
            if (cur.getData().compareTo(data) < 0) {
                if (cur.getRight() != null)
                    cur = cur.getRight();
                else return false;
            }
            else if (cur.getData().compareTo(data) > 0) {
                if (cur.getLeft() != null)
                    cur = cur.getLeft();
                else return false;
            }
            else if (cur.getData().compareTo(data) == 0) {
                cur = null;
                size--;
                removed = true;
            }
        }
        return true;
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

