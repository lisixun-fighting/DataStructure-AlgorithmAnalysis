package heap;

public class SkewHeap<T extends Comparable<? super T>> {
    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
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


    public SkewHeap() {
        root = null;
    }
    
    public void makeEmpty() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public void insert(T t) {
        root = merge(root, new Node<>(t));
    }
    
    public T findMin() {
        return root.element;
    }
    
    public T deleteMin() {
        T min = findMin();
        root = merge(root.left, root.right);
        return min;
    }
    
    public void merge(SkewHeap<T> rhs) {
        if (this == rhs)
            return;
        this.root = merge(this.root, rhs.root);
    }

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

    private Node<T> merge1(Node<T> h1, Node<T> h2) {
        if (h1.left == null)
            h1.left = h2;
        else {
            h1.right = merge(h1.right, h2);
            if (h1.right != null)
                swapChildren(h1);
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
        SkewHeap<Integer> h  = new SkewHeap<>();
        SkewHeap<Integer> h1 = new SkewHeap<>();
        int i = 37;

        for (i = 37; i != 0; i = (i + 37) % numItems) {
            if (i % 2 == 0)
                h1.insert(i);
            else
                h.insert(i);
        }
        h.merge(h1);
        for (i = 1; i < numItems; i++) {
            System.out.println(i);
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
        }
    }
}
