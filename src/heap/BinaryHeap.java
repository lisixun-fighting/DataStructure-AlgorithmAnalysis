package heap;

import java.util.Arrays;
import java.util.Random;

public class BinaryHeap<T extends Comparable<? super T>> {

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize+2) * 11 / 10];
        int i = 1;
        for(T item : items)
            array[i++] = item;
        buildHeap();
    }

    public void add(T x) {
        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);
        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole/2]) < 0; hole /= 2)
            array[hole] = array[hole/2];
        array[hole] = x;
    }

    public T top() {
        if (isEmpty())
            throw new RuntimeException();
        return array[1];
    }

    public T remove() {
        if (isEmpty())
            throw new RuntimeException();
        T min = top();
        array[1] = array[currentSize--];
        percolateDown(1);
        return min;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void clear() {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;

    private T[] array;

    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    private void buildHeap() {
        for (int i = currentSize/2; i > 0; i--)
            percolateDown(i);
    }

    private void enlargeArray(int newSize) {
        array = Arrays.copyOf(array, newSize);
    }

    public static void main(String[] args) {
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>();
        int i = 37;
        for (i = 37; i != 0; i = (i + 37) % numItems)
            h.add(i);
        for (i = 1; i < numItems; i++)
            if (h.remove() != i)
                System.out.println("Oops! " + i);
    }
}
