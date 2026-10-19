/*
 * Rakesh Shrestha
 * Bellevue University
 * CSD-402 Java for Programmers
 * Module 6.2 Programming Assignment
 * October 18, 2026
 *
 * Test program for the Fan class. Creates one fan with the
 * no-argument constructor and one with the argument constructor,
 * then uses the getters, setters, and toString() to show how
 * the class works. It also tries some invalid values to show
 * that the error handling works.
 *
 * Attribution: Structure follows the Module 6 class examples by
 * Professor Darrell Payne.
 */
public class TestFan{

  public static void main(String [] args){

    try{

      /*
       * Fan 1 uses the no-argument (default) constructor
       */
      Fan fan1 = new Fan();
      printFan("Fan 1 (default constructor)", fan1);

      /*
       * Fan 2 uses the argument constructor
       */
      Fan fan2 = new Fan(Fan.MEDIUM, true, 10, "blue");
      printFan("Fan 2 (argument constructor)", fan2);

      /*
       * Use the getters to read Fan 2's values one at a time
       */
      System.out.println("Getters on Fan 2:");
      System.out.println("getSpeed() = " + fan2.getSpeed());
      System.out.println("isOn() = " + fan2.isOn());
      System.out.println("getRadius() = " + fan2.getRadius());
      System.out.println("getColor() = " + fan2.getColor());
      System.out.println();

      /*
       * Use the setters to change Fan 1
       */
      fan1.setOn(true);
      fan1.setSpeed(Fan.FAST);
      fan1.setRadius(8.5);
      fan1.setColor("yellow");
      printFan("Fan 1 after using the setters", fan1);

      /*
       * Turn Fan 2 off and slow it down
       */
      fan2.setOn(false);
      fan2.setSpeed(Fan.SLOW);
      printFan("Fan 2 after turning it off", fan2);

      /*
       * Try invalid values. Each one should be rejected and
       * the fan should keep its old value.
       */
      System.out.println("Testing invalid values on Fan 1:");
      trySpeed(fan1, 7);
      tryRadius(fan1, -3);
      tryColor(fan1, "");
      System.out.println();

      printFan("Fan 1 after the invalid values (unchanged)", fan1);

      /*
       * Try to create a fan with a bad argument
       */
      System.out.println("Creating a fan with a negative radius:");
      try{

        Fan badFan = new Fan(Fan.SLOW, true, -5, "red");
        printFan("Bad fan", badFan);
      }
      catch(IllegalArgumentException e){

        System.out.println("Error: " + e.getMessage());
      }
      System.out.println();
    }
    catch(Exception e){

      System.out.println("Unexpected error: " + e.getMessage());
    }
  }

  /*
   * Prints a heading and the fan's state
   */
  private static void printFan(String heading, Fan fan){

    System.out.println(heading + ":");
    System.out.println(fan);
    System.out.println();
  }

  /*
   * Helper methods that try a value and print the error if
   * the Fan class rejects it
   */
  private static void trySpeed(Fan fan, int speed){

    try{

      fan.setSpeed(speed);
      System.out.println("Speed set to " + speed);
    }
    catch(IllegalArgumentException e){

      System.out.println("Error: " + e.getMessage());
    }
  }

  private static void tryRadius(Fan fan, double radius){

    try{

      fan.setRadius(radius);
      System.out.println("Radius set to " + radius);
    }
    catch(IllegalArgumentException e){

      System.out.println("Error: " + e.getMessage());
    }
  }

  private static void tryColor(Fan fan, String color){

    try{

      fan.setColor(color);
      System.out.println("Color set to " + color);
    }
    catch(IllegalArgumentException e){

      System.out.println("Error: " + e.getMessage());
    }
  }
}
