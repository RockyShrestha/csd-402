/*
 *
 * Rakesh Shrestha
 * Bellevue University
 * CSD402-T301 Java for Programmers
 * Module 5.2 Programming Assignment
 * October 11, 2026
 *
 * Locate the Largest and Smallest Elements in Multiple Dimension Arrays
 *
 * This program finds the location of the largest and smallest element in a
 * two-dimensional array. Each locate method returns a one-dimensional array
 * with two elements: the row index and the column index.
 *
 * If the largest or smallest value appears more than once, the first one
 * found (row by row, left to right) is returned. Asymmetrical arrays work
 * too, and empty rows are skipped.
 *
 * Attribution:
 * The array setup, fill, and print methods follow the Module 5 examples by
 * Professor Darrell Payne (Example_101, Example_104). The locate methods are
 * based on Programming Exercise 8.13 in Liang, Y. D. (2024). Introduction to
 * Java Programming and Data Structures (13th ed.). Pearson.
 */
public class LocateElements{

  public static void main(String [] args){

    /*
     * Symmetrical double array, 3 rows, 4 columns, random values
     */
    double [][] doubleTable = new double [3][4];
    LocateElements.fillArray(doubleTable);

    /*
     * Symmetrical int array, 4 rows, 4 columns, random values
     */
    int [][] intTable = new int [4][4];
    LocateElements.fillArray(intTable);

    /*
     * Asymmetrical int array with negative numbers and a tie for largest
     */
    int [][] intTable2 = {
                           {7, -3, 22},
                           {15, 0},
                           {-8, 22, 4, 9}
                         };

    System.out.println("Double Array");
    LocateElements.printArray(doubleTable);
    LocateElements.printLocation("Largest", LocateElements.locateLargest(doubleTable));
    LocateElements.printLocation("Smallest", LocateElements.locateSmallest(doubleTable));

    System.out.println();

    System.out.println("Int Array");
    LocateElements.printArray(intTable);
    LocateElements.printLocation("Largest", LocateElements.locateLargest(intTable));
    LocateElements.printLocation("Smallest", LocateElements.locateSmallest(intTable));

    System.out.println();

    System.out.println("Asymmetrical Int Array");
    LocateElements.printArray(intTable2);
    LocateElements.printLocation("Largest", LocateElements.locateLargest(intTable2));
    LocateElements.printLocation("Smallest", LocateElements.locateSmallest(intTable2));

    System.out.println();

    /*
     * Error handling tests
     * Each of these should throw an exception that gets caught here
     */
    System.out.println("Error Handling Tests");

    try{
      double [][] nullTable = null;
      LocateElements.locateLargest(nullTable);
    }
    catch(IllegalArgumentException e){
      System.out.println("Null array test: " + e.getMessage());
    }

    try{
      int [][] emptyTable = new int [2][0];
      LocateElements.locateSmallest(emptyTable);
    }
    catch(IllegalArgumentException e){
      System.out.println("Empty array test: " + e.getMessage());
    }

    try{
      int [][] missingRowTable = new int [3][];
      missingRowTable[0] = new int [] {1, 2, 3};
      LocateElements.locateLargest(missingRowTable);
    }
    catch(IllegalArgumentException e){
      System.out.println("Missing row test: " + e.getMessage());
    }

    System.out.println();
  }

  /*
   * Returns the location {row, column} of the largest element
   * in a double array
   */
  public static int [] locateLargest(double [][] arrayParam){

    return LocateElements.locateElement(arrayParam, true);
  }

  /*
   * Returns the location {row, column} of the largest element
   * in an int array
   */
  public static int [] locateLargest(int [][] arrayParam){

    return LocateElements.locateElement(LocateElements.toDoubleArray(arrayParam), true);
  }

  /*
   * Returns the location {row, column} of the smallest element
   * in a double array
   */
  public static int [] locateSmallest(double [][] arrayParam){

    return LocateElements.locateElement(arrayParam, false);
  }

  /*
   * Returns the location {row, column} of the smallest element
   * in an int array
   */
  public static int [] locateSmallest(int [][] arrayParam){

    return LocateElements.locateElement(LocateElements.toDoubleArray(arrayParam), false);
  }

  /*
   * Searches the array one time and keeps track of the best value so far.
   * findLargest is true to look for the largest value and false to look
   * for the smallest. All four locate methods use this so the loop is only
   * written once.
   */
  private static int [] locateElement(double [][] arrayParam, boolean findLargest){

    if(arrayParam == null){

      throw new IllegalArgumentException("The array cannot be null.");
    }

    int [] location = null;
    double bestValue = 0;

    for(int i = 0; i < arrayParam.length; ++i){

      if(arrayParam[i] == null){

        throw new IllegalArgumentException("Row " + i + " of the array was never created.");
      }

      for(int j = 0; j < arrayParam[i].length; ++j){

        // The first element becomes the starting value to compare against
        if(location == null){

          bestValue = arrayParam[i][j];
          location = new int [] {i, j};
        }
        else if(findLargest && arrayParam[i][j] > bestValue){

          bestValue = arrayParam[i][j];
          location = new int [] {i, j};
        }
        else if(!findLargest && arrayParam[i][j] < bestValue){

          bestValue = arrayParam[i][j];
          location = new int [] {i, j};
        }
      }
    }

    // No elements were found, so there is nothing to locate
    if(location == null){

      throw new IllegalArgumentException("The array does not have any elements.");
    }

    return location;
  }

  /*
   * Copies an int array into a double array so the int methods can use
   * the same search as the double methods. Every int value fits in a
   * double, so nothing is lost. Null rows stay null so locateElement
   * can report them.
   */
  private static double [][] toDoubleArray(int [][] arrayParam){

    if(arrayParam == null){

      return null;
    }

    double [][] doubleArray = new double [arrayParam.length][];

    for(int i = 0; i < arrayParam.length; ++i){

      if(arrayParam[i] != null){

        doubleArray[i] = new double [arrayParam[i].length];

        for(int j = 0; j < arrayParam[i].length; ++j){

          doubleArray[i][j] = arrayParam[i][j];
        }
      }
    }

    return doubleArray;
  }

  /*
   * Fills a double array with random values from 10.0 to 99.9
   */
  public static void fillArray(double [][] arrayParam){

    for(int i = 0; i < arrayParam.length; ++i){

      for(int j = 0; j < arrayParam[i].length; ++j){

        arrayParam[i][j] = (int)(Math.random() * 900 + 100) / 10.0;
      }
    }
  }

  /*
   * Fills an int array with random values from 10 to 99
   */
  public static void fillArray(int [][] arrayParam){

    for(int i = 0; i < arrayParam.length; ++i){

      for(int j = 0; j < arrayParam[i].length; ++j){

        arrayParam[i][j] = (int)(Math.random() * 90 + 10);
      }
    }
  }

  /*
   * Prints each element of a double array with its location
   */
  public static void printArray(double [][] arrayParam){

    for(int i = 0; i < arrayParam.length; ++i){

      for(int j = 0; j < arrayParam[i].length; ++j){

        System.out.print("[" + i + "][" + j + "] = " + arrayParam[i][j] + " ");
      }

      System.out.println();
    }
  }

  /*
   * Prints each element of an int array with its location
   */
  public static void printArray(int [][] arrayParam){

    for(int i = 0; i < arrayParam.length; ++i){

      for(int j = 0; j < arrayParam[i].length; ++j){

        System.out.print("[" + i + "][" + j + "] = " + arrayParam[i][j] + " ");
      }

      System.out.println();
    }
  }

  /*
   * Prints the location returned by one of the locate methods
   */
  public static void printLocation(String label, int [] location){

    System.out.println(label + " element is at [" + location[0] + "][" + location[1] + "]");
  }
}
