package list;

public class MyQueue<T> {
    private int theSize;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    private static class Node<T> {
        public Node(T d, Node<T> p, Node<T> n) {
            data = d;
            previous = p;
            next = n;
        }
        public T data;
        public Node<T> previous;
        public Node<T> next;
    }

    public MyQueue() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, null, null);
        beginMarker.next = endMarker;
        endMarker.previous = beginMarker;
        theSize = 0;
    }

    public void offer(T t) {
        Node<T> node = new Node<>(t, endMarker.previous, endMarker);
        endMarker.previous.next = node;
        endMarker.previous = node;
        theSize++;
    }

    public T poll() {
        Node<T> node = beginMarker.next;
        beginMarker.next = node.next;
        node.next.previous = beginMarker;
        theSize--;
        return node.data;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        while (!queue.isEmpty())
            System.out.print(queue.poll() + " ");
    }
}
