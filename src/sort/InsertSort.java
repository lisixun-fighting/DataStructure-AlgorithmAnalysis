package sort;

public class InsertSort implements SortStrategy {
    @Override
    public <T extends Comparable<? super T>> void sort(T[] array) {
        int p;
        for (int i = 1; i < array.length; i++) {
            T tmp = array[i];
            p = i;
            while (p > 0 && array[p-1].compareTo(tmp) > 0)
                array[p] = array[--p];
            array[p] = tmp;
        }
    }
}
