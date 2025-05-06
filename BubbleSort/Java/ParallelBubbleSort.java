
import java.util.*;

public class ParallelBubbleSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total no of elements => ");
        int len = sc.nextInt();
        System.out.println("Enter the elements => ");
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = sc.nextInt();
        }
        int n = array.length;

        // Sequential Bubble Sort
        long startTime = System.nanoTime();
        sequentialBubbleSort(array.clone());
        long endTime = System.nanoTime();
        System.out.println("Time taken by sequential algorithm: " + (endTime - startTime) / 1_000_000.0 + " milliseconds");

        // Parallel Bubble Sort
        startTime = System.nanoTime();
        parallelBubbleSort(array.clone());
        endTime = System.nanoTime();
        System.out.println("Time taken by parallel algorithm: " + (endTime - startTime) / 1_000_000.0 + " milliseconds");
    }

    // Sequential Bubble Sort
    public static void sequentialBubbleSort(int[] a) {
        int n = a.length;
        boolean swapped;
        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println("\nSorted array (sequential): " + Arrays.toString(a));
    }

    // Parallel Bubble Sort
    public static void parallelBubbleSort(int[] a) {
        int n = a.length;
        boolean swapped;
        for (int i = 0; i < n; i++) {
            swapped = false;
            int first = i % 2;
            // Parallel loop (using two threads, simulate parallelism)
            for (int j = first; j < n - 1; j += 2) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println("\nSorted array (parallel): " + Arrays.toString(a));
    }

    // Swap method
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
