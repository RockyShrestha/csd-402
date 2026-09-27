/*
 * Name:        Rakesh Shrestha
 * Course:      CSD-402 Java for Programmers
 * Assignment:  Module 3.2 Programming Assignment
 * Date:        September 27, 2026
 *
 * Purpose:
 * This program prints a pyramid of numbers using nested for loops.
 * Each row starts at 1, doubles up to the middle value (a power of 2),
 * and then halves back down to 1. An @ symbol is printed at the end of
 * every row, and all of the @ symbols line-up in the same column.
 *
 * How it works:
 * - The outer loop controls the row number (0 through 6, so 7 rows).
 * - Inside it, four inner loops handle one row:
 *     1. leading spaces that push the numbers toward the center
 *     2. the left side of the row, doubling each time (1 2 4 8 ...)
 *     3. the right side of the row, halving each time (... 8 4 2 1)
 *     4. trailing spaces so the @ symbol always ends up in the same spot
 * - Every number is printed in a field 4 characters wide, so the columns
 *   stay lined up even when the numbers go from 1 digit to 2 digits.
 */
public class NumberPyramid {

    public static void main(String[] args) {

        final int ROWS = 7;        // number of rows in the pyramid
        final int FIELD_WIDTH = 4; // width used to print each number

        // Print two blank lines so the pyramid doesn't sit right under
        // the run command in the console
        System.out.println();
        System.out.println();

        // Outer loop: one pass per row
        for (int row = 0; row < ROWS; row++) {

            // Number of empty "slots" on each side of this row
            int emptySlots = ROWS - 1 - row;

            // 1. Leading spaces
            for (int s = 0; s < emptySlots * FIELD_WIDTH; s++) {
                System.out.print(" ");
            }

            // 2. Left half of the row, including the middle number.
            //    Start at 1 and double it each time.
            int value = 1;
            for (int col = 0; col <= row; col++) {
                System.out.printf("%" + FIELD_WIDTH + "d", value);
                value *= 2;
            }

            // After the last doubling, value is one step past the middle.
            // Divide by 4 to get the number right after the middle.
            value /= 4;

            // 3. Right half of the row. Halve the value each time.
            for (int col = 0; col < row; col++) {
                System.out.printf("%" + FIELD_WIDTH + "d", value);
                value /= 2;
            }

            // 4. Trailing spaces so every @ lands in the same column
            for (int s = 0; s < emptySlots * FIELD_WIDTH; s++) {
                System.out.print(" ");
            }

            // End the row with the @ symbol
            System.out.println("  @");
        }
    }
}
