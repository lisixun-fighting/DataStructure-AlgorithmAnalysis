package heap;

import exception.UnderflowException;

public class LeftistHeap<T extends Comparable<? super T>> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        int npl;
        Node(T element) {
            this(element, null, null);
        }
        Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private Node<T> root;

    public LeftistHeap() {
        root = null;
    }

    public void insert(T x) {
        root = merge(root, new Node<>(x));
    }

    public T deleteMin() {
        if (isEmpty())
            throw new UnderflowException();
        T min = findMin();
        root = merge(root.left, root.right);
        return min;
    }

    public T findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return root.element;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    public void merge(LeftistHeap<T> rhs) {
        if (this == rhs)
            return;
        this.root = merge(this.root, rhs.root);
        rhs.makeEmpty();
    }

    /**
     * Internal method to merge roots.
     * Deal with deviant cases and calls recursive merge1
     * @param h1 this root
     * @param h2 rhs root
     * @return merged root
     */
    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        if (h1.element.compareTo(h2.element) < 0)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);
    }

    /**
     * Internal method to merge two roots.
     * Assumes trees are not empty, and h1's root contains smallest item
     * @param h1 subtree with smaller root
     * @param h2 subtree with larger root
     * @return merged root
     */
    private Node<T> merge1(Node<T> h1, Node<T> h2) {
        if (h1.left == null) // Single node
            h1.left = h2;
        else {
            h1.right = merge(h1.right, h2);
            if (h1.left.npl < h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private void swapChildren(Node<T> t) {
        Node<T> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }


    public static void main(String [] args) {
        int numItems = 100;
        LeftistHeap<Integer> h  = new LeftistHeap<>();
        LeftistHeap<Integer> h1 = new LeftistHeap<>();
        int i = 37;

        for (i = 37; i != 0; i = (i + 37) % numItems) {
            if (i % 2 == 0)
                h1.insert(i);
            else
                h.insert(i);
        }
        h.merge(h1);
        for (i = 1; i < numItems; i++) {
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
        }
    }
}
