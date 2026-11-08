import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/*
 *
 * Rakesh Shrestha
 * Bellevue University
 * CSD402-T301 Java for Programmers
 * Professor Darrell Payne
 * Module 9.2 Programming Assignment - Program 1
 * November 7, 2026
 *
 * This program fills an ArrayList with 10 Strings and prints them with a
 * for-each loop. It then asks the user which element they want to see again.
 * The user's answer is read as a String, converted to an int, and autoboxed
 * into an Integer. When the Integer is used as the index, it is auto-unboxed
 * back to an int. If the index is not in the list, the
 * IndexOutOfBoundsException is caught and an "Out of Bounds" message is shown.
 *
 * References:
 *   Liang, Y. D. (2024). Introduction to Java programming and data structures
 *     (13th ed.), Chapter 12: Exception Handling and Text I/O. Pearson.
 *   Payne, D. Module 9 Exceptions and Try/Finally examples. Bellevue University.
 *   W3Schools. Java Exceptions. https://www.w3schools.com/java/java_try_catch.asp
 */
public class ArrayListExceptionDemo{

  public static void main(String[] args){

    ArrayList<String> cities = buildCityList();

    printList(cities);

    Scanner input = new Scanner(System.in);

    try{

      System.out.print("\nWhich element would you like to see again? Enter 0 to "
          + (cities.size() - 1) + ": ");

      String userInput = input.nextLine().trim();

      showElement(cities, userInput);
    }
    catch(NoSuchElementException nsee){

      // Thrown by nextLine() if the input stream ends before a line is entered.
      System.out.println("\nAn exception has been thrown: No input was entered.");
    }
    finally{

      // The finally block runs no matter what happens above,
      // so the Scanner always gets closed.
      input.close();
      System.out.println("\nProgram finished.");
    }
  }

  /*
   * Creates the ArrayList and adds 10 city names to it.
   */
  public static ArrayList<String> buildCityList(){

    ArrayList<String> list = new ArrayList<>();

    list.add("Kathmandu");
    list.add("Pokhara");
    list.add("Omaha");
    list.add("Dallas");
    list.add("Chicago");
    list.add("Denver");
    list.add("Seattle");
    list.add("Austin");
    list.add("Boston");
    list.add("Miami");

    return list;
  }

  /*
   * Prints every element in the list using a for-each loop.
   * A counter is kept so the user can see each element's index.
   */
  public static void printList(ArrayList<String> list){

    System.out.println("ArrayList contents:");

    int index = 0;

    for(String city : list){

      System.out.println("  [" + index + "] " + city);
      index++;
    }
  }

  /*
   * Tries to print the element the user asked for.
   *
   * Integer.parseInt() returns a primitive int. Assigning it to an
   * Integer variable is autoboxing. Passing that Integer to list.get(),
   * which expects an int, is auto-unboxing.
   */
  public static void showElement(ArrayList<String> list, String userInput){

    try{

      Integer position = Integer.parseInt(userInput);   // autoboxing: int -> Integer

      String element = list.get(position);              // auto-unboxing: Integer -> int

      System.out.println("Element " + position + " is: " + element);
    }
    catch(IndexOutOfBoundsException ioobe){

      System.out.println("An exception has been thrown: Out of Bounds");
      System.out.println("Details: " + ioobe.getMessage());
    }
    catch(NumberFormatException nfe){

      System.out.println("An exception has been thrown: \"" + userInput
          + "\" is not a whole number.");
    }
  }
}
