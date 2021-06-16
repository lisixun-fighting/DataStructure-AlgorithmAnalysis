package tree;

import java.nio.BufferUnderflowException;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class TreeNode<T> {
        T val;
        TreeNode<T> left;
        TreeNode<T> right;
        public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public TreeNode(T val) {
            this.val = val;
        }
    }
    private TreeNode<T> root;
    public BinarySearchTree() { root = null; }
    public void makeEmpty() { root = null; }
    public boolean isEmpty() { return root == null; }
    public boolean contains(T x) {
        return contains(x, root);
    }
    public T findMin() {
        if(isEmpty()) throw new BufferUnderflowException();
        return findMin(root).val;
    }
    public T findMax() {
        if(isEmpty()) throw new BufferUnderflowException();
        return findMax(root).val;
    }
    public void insert(T x) {
        root = insert(x, root);
    }
    public TreeNode<T> remove(T x) {
        return remove(x, root);
    }
    public void printTree() {
        if(isEmpty())
            System.out.println("Empty Tree");
        else
            printTree(root);
    }

    private void printTree(TreeNode<T> root) {
        if(root != null) {
            System.out.print(root.val + " ");
            printTree(root.left);
            printTree(root.right);
        }
    }

    private boolean contains(T x, TreeNode<T> root) {
        if(isEmpty()) return false;
        if(x.compareTo(root.val) == 0)
            return true;
        if(x.compareTo(root.val) < 0)
            return contains(x, root.left);
        return contains(x, root.right);
    }

    private TreeNode<T> findMin(TreeNode<T> root) {
        if(root.left == null)
            return root;
        return findMin(root.left);
    }

    private TreeNode<T> findMax(TreeNode<T> root) {
        if(root.right == null)
            return root;
        return findMax(root.right);
    }

    private TreeNode<T> insert(T x, TreeNode<T> root) {
        if(root == null) {
            return new TreeNode<>(x);
        }
        else if (x.compareTo(root.val) < 0)
            root.left = insert(x, root.left);
        else if (x.compareTo(root.val) > 0)
            root.right = insert(x, root.right);
        return root;
    }

    private TreeNode<T> remove(T x, TreeNode<T> root) {
        if(root == null) {
            return null;
        }
        else if (x.compareTo(root.val) < 0)
            root.left = remove(x, root.left);
        else if (x.compareTo(root.val) > 0)
            root.right = remove(x, root.right);
        else if (root.left != null && root.right != null) {
            root.val = findMin(root.right).val;
            root.right = remove(root.val, root.right);
        }
        else
            root = (root.left == null) ? root.right : root.left;
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.printTree();
        tree.insert(10);
//        tree.printTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        tree.printTree();

//        System.out.println(tree.root.val.compareTo(10));
    }
}
