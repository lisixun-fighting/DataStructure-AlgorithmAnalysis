package heap;

import exception.UnderflowException;

import java.util.Arrays;

public class BinomialQueue<T extends Comparable<? super T>> {
    public BinomialQueue() {
        theTrees = new Node[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(T item) {
        currentSize = 1;
        theTrees = new Node[1];
        theTrees[0] = new Node<>(item);
    }

    public void merge(BinomialQueue<T> rhs) {
        if (this == rhs)
            return;
        currentSize += rhs.currentSize;
        if (currentSize > capacity()) {
            int maxLength = Math.max(this.theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }
        Node<T> carry = null;
        for (int i = 0, j = 1; i < theTrees.length; i++, j *= 2) {
            Node<T> t1 = theTrees[i];
            Node<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;
            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;
            switch (whichCase) {
                case 0:
                case 1:
                    break;
                case 2:
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 3:
                    assert t1 != null;
                    assert t2 != null;
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 4:
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 5:
                    assert carry != null;
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6:
                    assert carry != null;
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7:
                    theTrees[i] = carry;
                    assert t1 != null;
                    assert t2 != null;
                    carry = combineTrees(t1, t2);
                    break;
            }
        }
        rhs.makeEmpty();
    }

    public void insert(T x) {
        merge(new BinomialQueue<>(x));
    }

    public T findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return theTrees[findMinIndex()].element;
    }

    public T deleteMin() {
        if (isEmpty())
            throw new UnderflowException();
        int minIndex = findMinIndex();
        T minItem = theTrees[minIndex].element;
        Node<T> deletedTree = theTrees[minIndex].leftChild;

        BinomialQueue<T> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTrees(minIndex + 1);

        deletedQueue.currentSize = deletedQueue.capacity();

        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;
        merge(deletedQueue);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        Arrays.fill(theTrees, null);
    }

    private static class Node<T> {
        T element;
        Node<T> leftChild;
        Node<T> nextSibling;
        int npl;
        Node(T element) {
            this(element, null, null);
        }
        Node(T element, Node<T> leftChild, Node<T> nextSibling) {
            this.element = element;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }
    }

    private static final int DEFAULT_TREES = 1;
    private int currentSize;
    private Node<T>[] theTrees;

    private void expandTheTrees(int newNumTrees) {
        theTrees = Arrays.copyOf(theTrees, newNumTrees);
    }

    /**
     * Return the result of merging equal-sized t1 and t2
     * @param t1
     * @param t2
     * @return
     */
    private Node<T> combineTrees(Node<T> t1, Node<T> t2) {
        if (t1.element.compareTo(t2.element) > 0)
            return combineTrees(t2, t1);
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }

    private int findMinIndex() {
        int minIndex = -1;
        for (int i = 0; i < theTrees.length; i++) {
            if (theTrees[i] != null)
                if (minIndex == -1)
                    minIndex = i;
                else
                    minIndex = theTrees[i].element.compareTo(
                            theTrees[minIndex].element) < 0 ? i : minIndex;
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int numItems = 10000;
        BinomialQueue<Integer> h  = new BinomialQueue<>( );
        BinomialQueue<Integer> h1 = new BinomialQueue<>( );
        int i = 37;

        System.out.println( "Starting check." );

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            if( i % 2 == 0 )
                h1.insert( i );
            else
                h.insert( i );

        h.merge( h1 );
        for( i = 1; i < numItems; i++ ) {
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
        }
        System.out.println( "Check done." );
    }
}
