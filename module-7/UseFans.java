/*
 * Rakesh Shrestha
 * Bellevue University
 * CSD-402 Java for Programmers
 * Module 7.2 Programming Assignment
 * October 25, 2026
 *
 * UseFans creates a collection (ArrayList) of Fan instances and
 * displays them two ways:
 *   displayFans() takes the whole collection
 *   displayFan()  takes a single Fan
 *
 * Neither display method calls toString(). They build the output
 * from the Fan getters instead. The test code in main() changes the
 * fans with the setters, tries invalid values, and tries to create
 * an invalid fan to show that the error handling works for every
 * fan in the collection.
 *
 * Attribution: Passing objects to methods follows the Module 7
 * Methods/Objects examples by Professor Darrell Payne (Example_2_02).
 * ArrayList usage is based on Liang, Introduction to Java Programming
 * and Data Structures, Chapter 11, and the W3Schools Java ArrayList
 * tutorial.
 */
import java.util.ArrayList;

public class UseFans{

  public static void main(String [] args){

    UseFans useFans = new UseFans();

    try{

      /*
       * Build the collection. Fan 1 uses the default constructor
       * and the rest use the argument constructor.
       */
      ArrayList<Fan> fans = new ArrayList<>();

      fans.add(new Fan());
      fans.add(new Fan(Fan.SLOW, true, 8, "black"));
      fans.add(new Fan(Fan.MEDIUM, false, 10, "blue"));
      fans.add(new Fan(Fan.FAST, true, 12.5, "silver"));

      useFans.printHeading("Starting fans (" + fans.size() + " in the collection)");
      useFans.displayFans(fans);

      /*
       * Turn every fan on at MEDIUM
       */
      useFans.setAllFans(fans, true, Fan.MEDIUM);
      useFans.printHeading("After turning every fan on at MEDIUM");
      useFans.displayFans(fans);

      /*
       * Change one fan by itself and display only that fan
       */
      Fan firstFan = fans.get(0);
      firstFan.setSpeed(Fan.FAST);
      firstFan.setRadius(7.5);
      firstFan.setColor("red");

      useFans.printHeading("Fan 1 after changing speed, radius, and color");
      useFans.displayFan(firstFan);

      /*
       * Try invalid values on every fan. Each one should be
       * rejected and the fan should keep its old values.
       */
      useFans.printHeading("Trying invalid values on every fan");
      useFans.tryInvalidValues(fans);

      useFans.printHeading("Fans after the invalid values (unchanged)");
      useFans.displayFans(fans);

      /*
       * Try to add a fan with a bad radius. The constructor should
       * reject it, so the collection should still have 4 fans.
       */
      useFans.printHeading("Trying to add a fan with a radius of 0");
      try{

        fans.add(new Fan(Fan.SLOW, true, 0, "green"));
      }
      catch(IllegalArgumentException e){

        System.out.println("Error: " + e.getMessage());
      }
      System.out.println("Fans in the collection: " + fans.size());
      System.out.println();

      /*
       * Turn every fan off for the final state
       */
      useFans.setAllFans(fans, false, Fan.STOPPED);
      useFans.printHeading("Final state after turning every fan off");
      useFans.displayFans(fans);

      /*
       * Show that displayFans() handles an empty collection
       */
      useFans.printHeading("Displaying an empty collection");
      useFans.displayFans(new ArrayList<Fan>());
    }
    catch(Exception e){

      System.out.println("Unexpected error: " + e.getMessage());
    }
  }

  /*
   * Displays every fan in the collection. Calls displayFan() for
   * each one so the format only lives in one place.
   */
  public void displayFans(ArrayList<Fan> fans){

    if(fans == null || fans.isEmpty()){

      System.out.println("There are no fans to display.");
      System.out.println();
      return;
    }

    for(int i = 0; i < fans.size(); i++){

      System.out.println("Fan " + (i + 1) + ":");
      this.displayFan(fans.get(i));
    }
  }

  /*
   * Displays one fan using its getters (not toString())
   */
  public void displayFan(Fan fan){

    if(fan == null){

      System.out.println("There is no fan to display.");
      System.out.println();
      return;
    }

    System.out.println("  On/Off = " + (fan.isOn() ? "on" : "off"));
    System.out.println("  Speed  = " + fan.getSpeedName() + " (" + fan.getSpeed() + ")");
    System.out.println("  Radius = " + fan.getRadius());
    System.out.println("  Color  = " + fan.getColor());
    System.out.println();
  }

  /*
   * Turns every fan in the collection on or off and sets the
   * same speed on each one
   */
  public void setAllFans(ArrayList<Fan> fans, boolean on, int speed){

    for(Fan fan : fans){

      fan.setOn(on);
      fan.setSpeed(speed);
    }
  }

  /*
   * Tries a bad speed, radius, and color on each fan and prints
   * the error message from the Fan class
   */
  public void tryInvalidValues(ArrayList<Fan> fans){

    for(int i = 0; i < fans.size(); i++){

      Fan fan = fans.get(i);

      System.out.println("Fan " + (i + 1) + ":");

      try{

        fan.setSpeed(5);
      }
      catch(IllegalArgumentException e){

        System.out.println("  Error: " + e.getMessage());
      }

      try{

        fan.setRadius(-2);
      }
      catch(IllegalArgumentException e){

        System.out.println("  Error: " + e.getMessage());
      }

      try{

        fan.setColor(" ");
      }
      catch(IllegalArgumentException e){

        System.out.println("  Error: " + e.getMessage());
      }

      System.out.println();
    }
  }

  /*
   * Prints a section heading so the output is easy to follow
   */
  public void printHeading(String title){

    System.out.println("===== " + title + " =====");
    System.out.println();
  }
}
