package list;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    
    private int theSize;
    private T[] theItems;
    
    public MyArrayList() {
        doClear();
    }
    
    public void clear() {
        doClear();
    }
    
    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }
    
    public int size() {
        return theSize;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public void trimToSize() {
        ensureCapacity(size());
    }
    
    public T get(int index) {
        if(index >= size() || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        return theItems[index];
    }

    public T set(int index, T newVal) {
        if(index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    private void ensureCapacity(int newCapacity) {
        if(newCapacity < theSize)
            return;
        T[] old = theItems;
        // O(N)时间复杂度
        theItems = (T[])new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int index, T t) {
        if(theItems.length == size())
            ensureCapacity(size()*2 + 1);
        // O(N)时间复杂度
        for (int i = size(); i > index; i--)
            theItems[i] = theItems[i-1];
        theItems[index] = t;
        theSize++;
    }

    public T remove(int index) {
        T removed = theItems[index];
        for (int i = index; i < size(); i++)
            theItems[i] = theItems[i+1];
        theSize--;
        ensureCapacity(theItems.length-1);
        return removed;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<T> {
        private int curr = 0;
        @Override
        public boolean hasNext() {
            return curr < size();
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            return theItems[curr++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--curr);
        }
    }

    @Override
    public String toString() {
        return "MyArrayList" + Arrays.toString(Arrays.copyOf(theItems, size()));
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);

        System.out.println(list);
        System.out.println();
        System.out.println(list.get(2));

        list.set(2, 3);
        System.out.println(list);

        for (int i = 0; i < 10; i++)
            list.add(2,i);

        System.out.println(list);
        list.doClear();

        System.out.println(list);
    }
}
