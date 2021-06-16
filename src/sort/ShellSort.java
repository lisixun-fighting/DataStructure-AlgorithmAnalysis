package sort;

public class ShellSort implements SortStrategy {
    @Override
    public <T extends Comparable<? super T>> void sort(T[] array) {
        int p;
        for (int gap = array.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                T tmp = array[i];
                for (p = i; p >= gap && array[p-gap].compareTo(tmp) > 0; p -= gap)
                    array[p] = array[p - gap];
                array[p] = tmp;
            }
        }
    }
}
