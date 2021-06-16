package sort;

import java.util.Arrays;

/**
 * Strategy Method
 * @param <T>
 */
public class App<T extends Comparable<? super T>> {

    SortStrategy strategy;

    public App(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void mySort(T[] array) {
        strategy.sort(array);
    }

    public static void main(String[] args) {
        Integer[] array = {2, 4, 5, 1, 3};
        App<Integer> app = new App<>(new HeapSort());
//        mergeSort(array);
        app.mySort(array);
        System.out.println(Arrays.toString(array));
    }
}
