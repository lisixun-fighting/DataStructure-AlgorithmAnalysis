package sort;

import java.util.Arrays;

public class HeapSort implements SortStrategy {
    @Override
    public <T extends Comparable<? super T>> void sort(T[] array) {
        // rebuild binary heap
        for (int i = array.length/2 - 1; i >= 0; i--)
            percolateDown(array, i, array.length);
        // swap and percolate
        for (int i = array.length - 1; i >= 0; i--) {
            T max = array[0];
            array[0] = array[i];
            array[i] = max;
            percolateDown(array, 0, i);
        }
    }

    private <T extends Comparable<? super T>> void percolateDown(T[] array, int i, int size) {
        T obj = array[i];
        int j;
        for (; i*2+1 < size; i = j) {
            j = i*2 + 1;
            if(j+1 < size && array[j+1].compareTo(array[j]) > 0)
                j++;

            if (obj.compareTo(array[j]) < 0)
                array[i] = array[j];
            else
                break;
        }
        array[i] = obj;
    }
}
