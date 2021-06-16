package sort;

public interface SelectStrategy {

    <T extends Comparable<? super T>> void select(T[] a, int k);

}
