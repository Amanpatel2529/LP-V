
import java.util.*;

public class ParallelMergeSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements: ");
        int len = sc.nextInt();

        int[] array = new int[len];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < len; i++) {
            array[i] = sc.nextInt();
        }

        int n = array.length;

        // Sequential Merge Sort
        long startTime = System.nanoTime();
        sequentialMergeSort(array.clone(), 0, n - 1);
        long endTime = System.nanoTime();
        System.out.println("Sequential Time: " + (endTime - startTime) / 1_000_000.0 + " milliseconds");

        // Parallel Merge Sort
        startTime = System.nanoTime();
        parallelMergeSort(array.clone(), 0, n - 1);
        endTime = System.nanoTime();
        System.out.println("Parallel Time: " + (endTime - startTime) / 1_000_000.0 + " milliseconds");

        // Print sorted array
        System.out.println("\nSorted array: " + Arrays.toString(array));
    }

    // Sequential Merge Sort
    public static void sequentialMergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sequentialMergeSort(a, left, mid);
            sequentialMergeSort(a, mid + 1, right);
            merge(a, left, mid, mid + 1, right);
        }
    }

    // Parallel Merge Sort
    public static void parallelMergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Parallelize the recursion for the left and right halves
            Thread leftThread = new Thread(() -> parallelMergeSort(a, left, mid));
            Thread rightThread = new Thread(() -> parallelMergeSort(a, mid + 1, right));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(a, left, mid, mid + 1, right);
        }
    }

    // Merge function to merge two sorted halves
    public static void merge(int[] a, int i1, int j1, int i2, int j2) {
        int[] temp = new int[a.length];
        int i = i1, j = i2, k = 0;

        while (i <= j1 && j <= j2) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= j1) {
            temp[k++] = a[i++];
        }

        while (j <= j2) {
            temp[k++] = a[j++];
        }

        for (i = i1, j = 0; i <= j2; i++, j++) {
            a[i] = temp[j];
        }
    }
}
