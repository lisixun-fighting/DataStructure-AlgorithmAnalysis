package sort;

public interface SortStrategy {

    <T extends Comparable<? super T>> void sort(T[] array);

}
