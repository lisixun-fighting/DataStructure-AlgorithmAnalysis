package prefix;

import java.util.Comparator;

public class Comparing {

    public static <T extends Comparable<? super T>> T findMax(T[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i].compareTo(arr[maxIndex]) > 0)
                maxIndex = i;
        }
        return arr[maxIndex];
    }

    public static <T> T findMax(T[] arr, Comparator<? super T> comparator) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if(comparator.compare(arr[i], arr[maxIndex]) > 0)
                maxIndex = i;
        }
        return arr[maxIndex];
    }

    public static void main(String[] args) {
        String[] arr = { "ZARA", "unique", "H&M" };
        String max = findMax(arr, new CaseInsensitiveComparator());
        System.out.println(max);
    }
}

class CaseInsensitiveComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}
