/*
 * Author:     Rakesh Shrestha
 * Course:     CSD 402 - Java for Programmers
 * Assignment: Module 4.2 Programming Assignment
 * Date:       2026/10/03
 *
 * Purpose:
 * This program has four overloaded methods named average(). Each one takes
 * an array of a different numeric type (short, int, long, or double) and
 * returns the average in that same type. The main method tests each method
 * with an array of a different size, prints the original elements, and
 * prints the average that was returned. It also tests the error handling by
 * passing an empty array and a null array.
 *
 * Note on the whole-number versions: short, int, and long cannot hold a
 * fraction, so their averages are truncated (for example, 7.75 becomes 7).
 * The double version keeps the decimal part.
 *
 * Code attribution:
 * Array concepts are based on the course textbook: Liang, Y. D.,
 * Introduction to Java Programming and Data Structures, 13th ed. (Pearson),
 * Chapter 7, Sections 7.2.7 (foreach loops) and 7.3 (computing an average).
 * Also used: W3Schools Java Arrays tutorial
 * (https://www.w3schools.com/java/java_arrays.asp). Arrays.toString() comes
 * from the standard java.util.Arrays class.
 */
import java.util.Arrays;

public class AverageOverload {

    public static void main(String[] args) {

        // Each array is a different size so each method is tested with its own length.
        short[] shortArray = {12, 7, 3, 9};                                    // 4 elements
        int[] intArray = {45, 82, 17, 66, 90, 31};                              // 6 elements
        long[] longArray = {150000L, 98000L, 210500L, 76000L,
                            134250L, 188900L, 99999L, 120000L};                 // 8 elements
        double[] doubleArray = {3.5, 8.25, 6.0, 9.75, 2.1};                     // 5 elements

        System.out.println("=============================================");
        System.out.println("  Module 4.2 - Overloaded average() Methods");
        System.out.println("=============================================");

        printResult("Test 1: short array", shortArray.length,
                Arrays.toString(shortArray), String.valueOf(average(shortArray)));

        printResult("Test 2: int array", intArray.length,
                Arrays.toString(intArray), String.valueOf(average(intArray)));

        printResult("Test 3: long array", longArray.length,
                Arrays.toString(longArray), String.valueOf(average(longArray)));

        printResult("Test 4: double array", doubleArray.length,
                Arrays.toString(doubleArray), String.format("%.2f", average(doubleArray)));

        System.out.println();
        System.out.println("Note: The short, int, and long averages drop the decimal part.");

        // Error handling tests: an empty array and a null array should both be
        // rejected, and the error message should be printed to the console.
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("  Error Handling Tests (bad input on purpose)");
        System.out.println("---------------------------------------------");

        try {
            int[] emptyArray = {};
            System.out.println("Average of empty array: " + average(emptyArray));
        } catch (IllegalArgumentException e) {
            System.out.println("Handled correctly (empty array): " + e.getMessage());
        }

        try {
            double[] nullArray = null;
            System.out.println("Average of null array: " + average(nullArray));
        } catch (IllegalArgumentException e) {
            System.out.println("Handled correctly (null array): " + e.getMessage());
        }
    }

    /**
     * Returns the average of a short array.
     * The sum is stored in a long so adding many short values cannot overflow.
     *
     * @param array the values to average
     * @return the average, with any decimal part dropped
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static short average(short[] array) {
        checkArray(array == null ? -1 : array.length);
        long sum = 0;
        for (short value : array) {
            sum += value;
        }
        return (short) (sum / array.length);
    }

    /**
     * Returns the average of an int array.
     * The sum is stored in a long so a large total cannot overflow an int.
     *
     * @param array the values to average
     * @return the average, with any decimal part dropped
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int average(int[] array) {
        checkArray(array == null ? -1 : array.length);
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (int) (sum / array.length);
    }

    /**
     * Returns the average of a long array.
     *
     * @param array the values to average
     * @return the average, with any decimal part dropped
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static long average(long[] array) {
        checkArray(array == null ? -1 : array.length);
        long sum = 0;
        for (long value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    /**
     * Returns the average of a double array, including the decimal part.
     *
     * @param array the values to average
     * @return the average
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static double average(double[] array) {
        checkArray(array == null ? -1 : array.length);
        double sum = 0.0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    /**
     * Checks that an array can be averaged. All four average() methods use
     * this one check, so the validation rules live in a single place.
     *
     * @param length the array length, or -1 if the array is null
     * @throws IllegalArgumentException if the array is null or empty
     */
    private static void checkArray(int length) {
        if (length == -1) {
            throw new IllegalArgumentException("The array is null, so no average can be calculated.");
        }
        if (length == 0) {
            throw new IllegalArgumentException("The array is empty, so no average can be calculated.");
        }
    }

    /**
     * Prints one test result in the same layout for every array type.
     *
     * @param title    the name of the test
     * @param size     the number of elements in the array
     * @param elements the array elements as text
     * @param average  the average as text
     */
    private static void printResult(String title, int size, String elements, String average) {
        System.out.println();
        System.out.println(title + " (" + size + " elements)");
        System.out.println("  Elements: " + elements);
        System.out.println("  Average : " + average);
    }
}
