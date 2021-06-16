package sort;

public class QuickSort implements SortStrategy, SelectStrategy {

    public <T extends Comparable<? super T>> void sort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    static final int CUTOFF = 16;

    private <T extends Comparable<? super T>> void quicksort(T[] array, int left, int right) {
        if (left + CUTOFF <= right) {
            T pivot = median3(array, left, right);
            int i = left, j = right-1;
            for (; ; ) {
                while (array[++i].compareTo(pivot) < 0) ;
                while (array[--j].compareTo(pivot) > 0) ;
                if (i < j)
                    swapReferences(array, i, j);
                else
                    break;
            }
            swapReferences(array, i, right-1);
            quicksort(array, left, i-1);
            quicksort(array, i, right);
        } else
            new InsertSort().sort(array);
    }

    private <T extends Comparable<? super T>> T median3(T[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[center].compareTo(array[left]) < 0)
            swapReferences(array, left, center);
        if (array[right].compareTo(array[left]) < 0)
            swapReferences(array, left, right);
        if (array[right].compareTo(array[center]) < 0)
            swapReferences(array, center, right);
        swapReferences(array, center, right-1);
        return array[right-1];
    }

    private <T extends Comparable<? super T>> void swapReferences(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Override
    public <T extends Comparable<? super T>> void select(T[] a, int k) {
        quickSelect(a, 0, a.length, k);
    }

    public <T extends Comparable<? super T>> void quickSelect(T[] a, int left, int right, int k) {
        if (left + CUTOFF <= right) {
            T pivot = median3(a, left, right);
            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) ;
                while (a[--j].compareTo(pivot) > 0) ;
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right-1);
            if (k <= i)
                quickSelect(a, 0, i-1, k);
            else
                quickSelect(a, i, right, k);
        } else {
            new InsertSort().sort(a);
        }
    }
}
