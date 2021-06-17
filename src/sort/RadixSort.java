package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RadixSort {
    /**
     * Radix sort for strings
     * Assume all are ASCII
     * Assume all are same length
     * @param arr string array
     * @param stringLen string length
     */
    @SuppressWarnings("unchecked")
    public static void RadixSortA(String[] arr, int stringLen) {
        final int BUCKETS = 256;
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];
        for (int i = 0; i < BUCKETS; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int i = stringLen-1; i >= 0; i--) {
            for (String s : arr) {
                buckets[s.charAt(i)].add(s);
            }
            int idx = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    arr[idx++] = s;
                }
                bucket.clear();
            }
        }
    }

    /**
     * Counting radix sort an array of Strings
     * Assume all are ASCII
     * Assume all are same length
     * @param arr array of strings
     * @param stringLen length of string
     */
    public static void countingRadixSort(String[] arr, int stringLen) {
        final int BUCKETS = 256;

        int N = arr.length;
        String[] buffer = new String[N];

        String[] in = arr;
        String[] out = buffer;

        for (int pos = stringLen - 1; pos >= 0; pos--) {
            int[] count = new int[BUCKETS+1];

            for (int i = 0; i < N; i++) {
                count[in[i].charAt(pos)+1]++;
            }
            for (int b = 1; b <= BUCKETS; b++) {
                count[b] += count[b-1];
            }
            for (int i = 0; i < N; i++) {
                out[count[in[i].charAt(pos)]++] = in[i];
            }
            // Swap in and out
            String[] tmp = in;
            in = out;
            out = tmp;
            // now in is sorted array, and wait to sort in next circle
            // odd circle, in is reference to arr
            // even circle, out is reference to arr
        }

        if (stringLen % 2 == 1) {
//            for (int i = 0; i < arr.length; i++) {
//                out[i] = in[i];
//            }
            arr = in;
        }
    }

    /**
     * Radix sort an array of Strings
     * Assume all are ASCII
     */
    @SuppressWarnings("unchecked")
    public static void radixSort(String[] arr, int maxLen) {
        final int BUCKETS = 256;

        ArrayList<String>[] wordsByLength = new ArrayList[maxLen+1];
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];

        for (int i = 0; i < wordsByLength.length; i++) {
            wordsByLength[i] = new ArrayList<>();
        }

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (String s : arr) {
            wordsByLength[s.length()].add(s);
        }

        int idx = 0;
        // sort words by length
        for (ArrayList<String> wordList : wordsByLength) {
            for (String s : wordList) {
                arr[idx++] = s;
            }
        }

        int startingIndex = arr.length;
        for (int pos = maxLen-1; pos >= 0; pos--) {
            startingIndex -= wordsByLength[pos+1].size();

            for (int i = startingIndex; i < arr.length; i++) {
                buckets[arr[i].charAt(pos)].add(arr[i]);
            }

            idx = startingIndex;
            for (ArrayList<String> thisBucket : buckets) {
                for (String s : thisBucket) {
                    arr[idx++] = s;
                }
                thisBucket.clear();
            }
        }
    }


    public static void main(String[] args) {
        Random r = new Random();
        String[] a1 = new String[10000];
        String[] a2 = new String[10000];
        int length = 10;
        for (int i = 0; i < a1.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                sb.append((char) r.nextInt(256));
            }
            a1[i] = a2[i] = sb.toString();
        }

        Arrays.sort(a1);
        RadixSort.radixSort(a2, 10);

        for (int i = 0; i < a1.length; i++) {
            if (!a1[i].equals(a2[i]))
                System.out.println("Oops!");
        }

    }
}
