package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private int theSize;
    private int modCount = 0;
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

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, null, null);
        beginMarker.next = endMarker;
        endMarker.previous = beginMarker;
        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int index, T t) {
        addBefore(getNode(index, 0 , size()), t);
    }

    public T set(int index, T newVal) {
        Node<T> p = getNode(index);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    private void addBefore(Node<T> p, T t) {
        Node<T> newNode = new Node<>(t, p.previous, p);
        p.previous.next = newNode;
        p.previous = newNode;
        theSize++;
        modCount++;
    }

    private Node<T> getNode(int index) {
        return getNode(index, 0, size()-1);
    }

    private Node<T> getNode(int index, int lower, int upper) {
        Node<T> p;
        if(index < lower || index > upper)
            throw new IndexOutOfBoundsException();
        if(index < size()/2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++)
                p = p.next;
        }
        else {
            p = endMarker;
            for (int i = 0; i < size()-index; i++)
                p = p.previous;
        }
        return p;
    }

    private T remove(Node<T> p) {
        p.next.previous = p.previous;
        p.previous.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> curr = beginMarker.next;
        private int expModCount = modCount;
        private boolean okToRemove = false;
        @Override
        public boolean hasNext() {
            return curr != endMarker;
        }
        @Override
        public T next() {
            if(modCount != expModCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();
            T val = curr.data;
            curr = curr.next;
            okToRemove = true;
            return val;
        }
        @Override
        public void remove() {
            if(modCount != expModCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(curr.previous);
            expModCount++;
            okToRemove = false;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("MyLinkedList{");
        if(size() == 0) return res.append("}").toString();
        LinkedListIterator it = new LinkedListIterator();
        while (it.hasNext()) {
            res.append(it.next()).append(",").append(" ");
        }
        res.replace(res.length()-2, res.length(), "}");
        return res.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);

        System.out.println(list);
        System.out.println();
        System.out.println(list.getNode(2));

        list.set(2, 3);
        System.out.println(list);

        for (int i = 0; i < 10; i++)
            list.add(2,i);

        System.out.println(list);
        list.doClear();

        System.out.println(list);
    }
}
