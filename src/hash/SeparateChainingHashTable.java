package hash;

import java.util.*;

public class SeparateChainingHashTable<T> {
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<>();
    }

    public void insert(T x) {
        List<T> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);
            if(++currentSize > theLists.length)
                rehash();
        }
    }

    public void remove(T x) {
        List<T> whichList = theLists[myhash(x)];
        if(whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(T x) {
        List<T> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    public void makeEmpty() {
        for (List<T> theList : theLists)
            theList.clear();
        currentSize = 0;
    }

    public static final int DEFAULT_TABLE_SIZE = 101;
    private List<T>[] theLists;
    private int currentSize;

    @SuppressWarnings("unchecked")
    private void rehash() {
        List<T>[] oldLists = theLists;
        theLists = new List[oldLists.length * 2];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
        currentSize = 0;
        for (List<T> oldList : oldLists)
            for (T t : oldList)
                insert(t);
    }

    private int myhash(T x) {
        int hash = x.hashCode();
        hash %= theLists.length;
        if (hash < 0)
            hash += theLists.length;
        return hash;
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        while (!isPrime( n ))
            n += 2;
        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n ) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
}
