import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/*
 *
 * Rakesh Shrestha
 * Bellevue University
 * CSD402-T301 Java for Programmers
 * Professor Darrell Payne
 * Module 9.2 Programming Assignment - Program 2
 * November 7, 2026
 *
 * This program creates a file named data.file if it does not already exist.
 * It then appends 10 randomly generated integers to the file, with each
 * integer separated by a space. The file is closed, opened again, read,
 * and its contents are displayed on the console. Running the program more
 * than once keeps adding 10 more numbers to the same file.
 *
 * References:
 *   Liang, Y. D. (2024). Introduction to Java programming and data structures
 *     (13th ed.), Chapter 12: Exception Handling and Text I/O. Pearson.
 *   Payne, D. Module 9 Files examples (Example_1_32). Bellevue University.
 *   W3Schools. Java Create and Write To Files.
 *     https://www.w3schools.com/java/java_files_create.asp
 */
public class DataFileDemo{

  private static final String FILE_NAME = "data.file";
  private static final int NUMBER_COUNT = 10;
  private static final int MAX_VALUE = 100;

  public static void main(String[] args){

    File file = new File(FILE_NAME);

    if(createFileIfMissing(file)){

      writeRandomNumbers(file);
      readAndDisplay(file);
    }
  }

  /*
   * Creates the file if it is not there yet.
   * Returns true when the file is ready to be used.
   */
  public static boolean createFileIfMissing(File file){

    try{

      if(file.createNewFile()){

        System.out.println("Created new file: " + file.getName());
      }
      else{

        System.out.println(file.getName() + " already exists. New numbers will be appended.");
      }

      return true;
    }
    catch(IOException ioe){

      System.out.println("An IOException has been thrown while creating "
          + file.getName() + ": " + ioe.getMessage());
      return false;
    }
  }

  /*
   * Appends 10 random integers (1 to 100) to the file.
   * Passing true to FileOutputStream turns on append mode,
   * so older numbers are kept.
   */
  public static void writeRandomNumbers(File file){

    PrintWriter writer = null;
    Random random = new Random();

    try{

      writer = new PrintWriter(new FileOutputStream(file, true));

      System.out.print("\nWriting " + NUMBER_COUNT + " numbers: ");

      for(int i = 0; i < NUMBER_COUNT; i++){

        int number = random.nextInt(MAX_VALUE) + 1;

        writer.print(number + " ");
        System.out.print(number + " ");
      }

      System.out.println();

      // PrintWriter does not throw IOException from print(), so checkError()
      // is used to find out if any of the writes failed.
      if(writer.checkError()){

        System.out.println("An error occurred while writing to " + file.getName() + ".");
      }
    }
    catch(IOException ioe){

      System.out.println("An IOException has been thrown while writing to "
          + file.getName() + ": " + ioe.getMessage());
    }
    finally{

      // Close the file whether or not the write worked.
      if(writer != null){

        writer.close();
      }
    }
  }

  /*
   * Reopens the file, reads every integer, and displays them.
   */
  public static void readAndDisplay(File file){

    Scanner reader = null;
    int count = 0;

    try{

      reader = new Scanner(file);

      System.out.println("\nContents of " + file.getName() + ":");

      while(reader.hasNextInt()){

        System.out.print(reader.nextInt() + " ");
        count++;
      }

      System.out.println("\n\nTotal numbers in file: " + count);
    }
    catch(IOException ioe){

      System.out.println("An IOException has been thrown while reading "
          + file.getName() + ": " + ioe.getMessage());
    }
    finally{

      if(reader != null){

        reader.close();
      }
    }
  }
}
