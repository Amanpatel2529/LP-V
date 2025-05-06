
import java.util.*;

public class ParallelReduction {

    public static void main(String[] args) {
        // Example array
        List<Integer> arr = Arrays.asList(5, 2, 9, 1, 7, 6, 8, 3, 4);

        // Min operation using parallel reduction
        int min = minReduction(arr);
        System.out.println("Minimum value: " + min);

        // Max operation using parallel reduction
        int max = maxReduction(arr);
        System.out.println("Maximum value: " + max);

        // Sum operation using parallel reduction
        int sum = sumReduction(arr);
        System.out.println("Sum: " + sum);

        // Average operation using parallel reduction
        double average = averageReduction(arr);
        System.out.println("Average: " + average);
    }

    // Min operation using parallel stream and reduction
    public static int minReduction(List<Integer> arr) {
        return arr.parallelStream()
                .reduce(Integer.MAX_VALUE, Integer::min); // Using Integer.MAX_VALUE as the identity value
    }

    // Max operation using parallel stream and reduction
    public static int maxReduction(List<Integer> arr) {
        return arr.parallelStream()
                .reduce(Integer.MIN_VALUE, Integer::max); // Using Integer.MIN_VALUE as the identity value
    }

    // Sum operation using parallel stream and reduction
    public static int sumReduction(List<Integer> arr) {
        return arr.parallelStream()
                .reduce(0, Integer::sum); // Using 0 as the identity value
    }

    // Average operation using parallel stream and reduction
    public static double averageReduction(List<Integer> arr) {
        return arr.parallelStream()
                .mapToInt(Integer::intValue) // Convert to primitive int stream
                .average() // Calculate the average
                .orElse(Double.NaN);           // Return NaN if empty list
    }
}
