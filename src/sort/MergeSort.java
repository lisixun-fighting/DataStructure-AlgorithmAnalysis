package sort;

public class MergeSort implements SortStrategy {

    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> void sort(T[] array) {
        mergeSort(array, (T[]) new Comparable<?>[array.length], 0, array.length-1);
    }

    private <T extends Comparable<? super T>> void mergeSort(T[] array,T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(array, tmpArray, left, center);
            mergeSort(array, tmpArray, center+1, right);
            merge(array, tmpArray, left, center+1, right);
        }
    }

    private <T extends Comparable<? super T>> void merge(T[] array, T[] tmpArray, int left, int right, int end) {
        int i = left;
        int j = right;
        int k = left;
        while (i < right && j < end+1) {
            if (array[i].compareTo(array[j]) < 0)
                tmpArray[k++] = array[i++];
            else
                tmpArray[k++] = array[j++];
        }
        while (i < right)
            tmpArray[k++] = array[i++];
        while (j < end+1)
            tmpArray[k++] = array[j++];
        do
            array[end] = tmpArray[end];
        while (--end >= left);
    }
}
