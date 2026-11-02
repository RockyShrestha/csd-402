/*
 * Rakesh Shrestha
 * CSD402-T301 Java for Programmers
 * Professor Darrell Payne
 * Bellevue University
 * Module 8.2 Programming Assignment
 * November 1, 2026
 *
 * Purpose:
 *   This program asks the user to enter whole numbers one at a time. Every
 *   number is stored in an ArrayList of Integer objects. When the user types
 *   0, the 0 is also added to the list and input stops. The list is then sent
 *   to the max method, which returns the largest value in the list (or 0 if
 *   the list is empty). The result is displayed to the user.
 *
 *   Before the user is asked for input, the program runs a few built-in test
 *   cases so the max method can be checked against known answers.
 *
 * How to compile and run:
 *   javac RakeshArrayListTest.java
 *   java RakeshArrayListTest
 *
 * Code attribution:
 *   Liang, Y. D. (2024). Introduction to Java Programming and Data
 *   Structures (13th ed.). Pearson. The loop that reads integers until 0 is
 *   entered follows LiveExample 11.9 (DistinctNumbers.java) in Chapter 11,
 *   Section 11.11. Section 11.12 shows Collections.max, which this program
 *   does not use because the assignment asks for a custom max method that
 *   returns 0 for an empty list.
 *   ArrayList and Scanner usage follows the Module 8 ArrayList examples by
 *   Professor Darrell Payne and the W3Schools Java ArrayList tutorial
 *   (https://www.w3schools.com/java/java_arraylist.asp).
 *   Written by Rakesh Shrestha.
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RakeshArrayListTest{

  // The value that tells the program the user is done entering numbers
  private static final int STOP_VALUE = 0;

  public static void main(String [] args){

    // Check the max method with fixed data before asking for user input
    runTests();

    // try-with-resources closes the Scanner automatically when we are done
    try(Scanner input = new Scanner(System.in)){

      ArrayList <Integer> numbers = readNumbers(input);

      Integer largest = max(numbers);

      System.out.println("\nYou entered: " + numbers);
      System.out.println("The largest value in the list is: " + largest);
    }
    catch(Exception e){

      // Safety net for anything unexpected so the program never just crashes
      System.out.println("An unexpected error occurred: " + e.getMessage());
    }
  }

  /*
   * Returns the largest value in the ArrayList.
   * If the list is empty (or null), the method returns 0.
   *
   * @param list an ArrayList of Integer values
   * @return the largest Integer in the list, or 0 if the list is empty
   */
  public static Integer max(ArrayList <Integer> list){

    if(list == null || list.isEmpty()){

      return 0;
    }

    // Start with the first element so negative numbers are handled correctly
    Integer largest = list.get(0);

    for(Integer value : list){

      if(value > largest){

        largest = value;
      }
    }

    return largest;
  }

  /*
   * Reads integers from the user until 0 is entered. Each valid integer,
   * including the final 0, is added to the ArrayList. Input that is not a
   * whole number is rejected with a message, and the user can try again.
   *
   * @param input the Scanner used to read from the keyboard
   * @return an ArrayList holding every integer the user entered
   */
  private static ArrayList <Integer> readNumbers(Scanner input){

    ArrayList <Integer> numbers = new ArrayList <Integer> ();

    System.out.println("\nEnter whole numbers one at a time.");
    System.out.println("Enter " + STOP_VALUE + " when you are finished.");

    while(true){

      System.out.print("Enter a number: ");

      try{

        int value = input.nextInt();

        numbers.add(value);

        if(value == STOP_VALUE){

          break;
        }
      }
      catch(InputMismatchException e){

        // Show what was typed, then throw away the bad token and ask again
        System.out.println("\"" + input.next() + "\" is not a valid whole number. "
                           + "Please try again.");
      }
      catch(NoSuchElementException e){

        // Happens if the input stream ends before 0 is entered
        System.out.println("\nNo more input was found. Using the numbers entered so far.");
        break;
      }
    }

    return numbers;
  }

  /*
   * Runs the max method against lists with known answers and prints
   * whether each test passed or failed.
   */
  private static void runTests(){

    System.out.println("Running tests for the max method...\n");

    int passed = 0;
    int total = 0;

    // Each test list is paired with the answer max should return
    ArrayList <ArrayList <Integer>> testLists = new ArrayList <> ();
    ArrayList <Integer> expected = new ArrayList <> ();

    testLists.add(new ArrayList <Integer> ());                          // empty list
    expected.add(0);

    testLists.add(buildList(0));                                         // only the stop value
    expected.add(0);

    testLists.add(buildList(4, 17, 9, 2, 0));                            // largest in the middle
    expected.add(17);

    testLists.add(buildList(55, 3, 12, 0));                              // largest first
    expected.add(55);

    testLists.add(buildList(-8, -3, -15, 0));                            // negatives, 0 is largest
    expected.add(0);

    testLists.add(buildList(-8, -3, -15));                               // all negatives, no 0
    expected.add(-3);

    testLists.add(buildList(7, 7, 7, 0));                                // duplicate values
    expected.add(7);

    testLists.add(buildList(Integer.MAX_VALUE, Integer.MIN_VALUE, 0));   // extreme values
    expected.add(Integer.MAX_VALUE);

    for(int i = 0; i < testLists.size(); ++i){

      total++;

      if(checkResult(testLists.get(i), expected.get(i))){

        passed++;
      }
    }

    // A null list should be treated the same as an empty list
    total++;

    if(checkResult(null, 0)){

      passed++;
    }

    System.out.println("\n" + passed + " of " + total + " tests passed.");
  }

  /*
   * Calls max on one list, compares the result to the expected answer,
   * and prints the outcome.
   *
   * @param list the list to test
   * @param expectedValue the answer max should return
   * @return true if the test passed, false otherwise
   */
  private static boolean checkResult(ArrayList <Integer> list, Integer expectedValue){

    Integer actual = max(list);
    boolean passed = actual.equals(expectedValue);

    System.out.println((passed ? "PASS" : "FAIL")
                       + "  list: " + list
                       + "  expected: " + expectedValue
                       + "  actual: " + actual);

    return passed;
  }

  /*
   * Helper that builds an ArrayList from any number of int values so the
   * test cases above stay short and readable.
   *
   * @param values the values to place in the list
   * @return a new ArrayList holding the values in order
   */
  private static ArrayList <Integer> buildList(int... values){

    ArrayList <Integer> list = new ArrayList <Integer> ();

    for(int value : values){

      list.add(value);
    }

    return list;
  }
}
