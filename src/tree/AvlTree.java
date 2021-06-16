package tree;

public class AvlTree<T extends Comparable<? super T>> {

    private static class AvlNode<T> {
        T val;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;
        public AvlNode(T val) {
            this.val = val;
        }
        public AvlNode(T val, AvlNode<T> left, AvlNode<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : Math.max(height(t.left), height(t.right)) + 1;
    }

    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);
        int compareResult = x.compareTo(t.val);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        return balance(t);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<T> balance(AvlNode<T> t) {
        if(t == null)
            return null;
        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if(height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        }
        return t;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> t) {
        t.left = rotateWithLeftChild(t.left);
        t = rotateWithLeftChild(t);
        return t;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> t) {
        AvlNode<T> tmp = t.left;
        t.left = tmp.right;
        tmp.right = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        tmp.height = Math.max(height(tmp.left), height(tmp.right)) + 1;
        return tmp;
    }

    private AvlNode<T> remove(T x, AvlNode<T> t) {
        if(t == null)
            return new AvlNode<>(x, null, null);
        int compareResult = x.compareTo(t.val);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.val = findMin(t.right).val;
            t.right = remove(t.val, t.right);
        }
        else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if(t.right == null)
            return t;
        return findMin(t.right);
    }
}
