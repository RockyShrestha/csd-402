/*
 * Rakesh Shrestha
 * Bellevue University
 * CSD-402 Java for Programmers
 * Module 11.3 Assignment: Redo of the Module 7.2 Programming Assignment
 * November 22, 2026
 *
 * Original assignment (Module 7.2): Starting with the Module 6 code,
 * create a class named UseFans that creates a collection of Fan
 * instances, has one method that displays a collection of fans and one
 * method that displays a single fan (neither uses toString()), uses the
 * 'this' reference throughout the Fan class where allowed, tests every
 * fan in the collection, and includes all of the Module 6 code.
 *
 * This file contains three classes:
 *   UseFans  - the Module 7 program (main class)
 *   Fan      - the Module 6 Fan class, updated to use 'this'
 *   TestFan  - the Module 6 test program, unchanged
 *
 * What changed in this redo:
 *   1. The feedback on the original submission said the code could not
 *      compile because there was no Fan class. In the original, Fan and
 *      TestFan were in separate files. They are now in this same file,
 *      below UseFans, so the program compiles from this one file. This
 *      follows the same layout Professor Payne uses in the Module 7
 *      examples, where the data class sits in the same file as the class
 *      with main().
 *   2. The fans are now built from text records. Each record is split
 *      and converted with the wrapper class methods Integer.parseInt(),
 *      Double.parseDouble(), and Boolean.parseBoolean(), so bad records
 *      are caught and reported instead of stopping the program.
 *   3. displayFan() builds its output with a StringBuilder, and the speed
 *      names come from one array instead of a switch statement.
 *   4. All caught exceptions are written to the console through one
 *      logError() method so every error message has the same format.
 *
 * How to compile and run (only this one file is needed):
 *   javac UseFans.java
 *   java UseFans     (Module 7 program)
 *   java TestFan     (Module 6 test program)
 *
 * Attribution: The Fan class is based on the Fan exercise in Liang,
 * Introduction to Java Programming and Data Structures, Chapter 9.
 * Passing objects to methods follows Professor Darrell Payne's Module 7
 * Methods/Objects example (Example_2_02). Wrapper class parsing and
 * StringBuilder follow Liang, Chapter 10, and the Module 7 Wrapper Class
 * and StringBuilder examples by Professor Payne.
 */
import java.util.ArrayList;
import java.util.List;

public class UseFans{

  /*
   * Text records used to build the collection. The format is
   * speed,on,radius,color. The last three records are bad on purpose
   * so the error handling can be seen in the output.
   */
  private static final String[] FAN_RECORDS = {
    "0,false,6,white",
    "1,true,8,black",
    "2,false,10,blue",
    "3,true,12.5,silver",
    "5,true,9,green",      // speed is out of range
    "2,true,ten,red",      // radius is not a number
    "1,true,7"             // color is missing
  };

  public static void main(String [] args){

    UseFans useFans = new UseFans();

    try{

      /*
       * Build the collection from the text records
       */
      useFans.printHeading("Building the fan collection from text records");
      List<Fan> fans = useFans.buildFans(FAN_RECORDS);
      System.out.println(fans.size() + " of " + FAN_RECORDS.length
          + " records were added to the collection.");
      System.out.println();

      /*
       * Display the whole collection
       */
      useFans.printHeading("Starting fans");
      useFans.displayFans(fans);

      /*
       * Turn every fan on at MEDIUM and display the collection again
       */
      useFans.setAllFans(fans, true, Fan.MEDIUM);
      useFans.printHeading("After turning every fan on at MEDIUM");
      useFans.displayFans(fans);

      /*
       * Change one fan and display only that fan
       */
      Fan firstFan = fans.get(0);
      firstFan.setSpeed(Fan.FAST);
      firstFan.setRadius(7.5);
      firstFan.setColor("red");

      useFans.printHeading("Fan 1 after changing its speed, radius, and color");
      useFans.displayFan(firstFan);

      /*
       * Try invalid values on every fan. Each one should be rejected,
       * and the fans should keep their old values.
       */
      useFans.printHeading("Trying invalid values on every fan");
      useFans.tryInvalidValues(fans);

      useFans.printHeading("Fans after the invalid values (no changes expected)");
      useFans.displayFans(fans);

      /*
       * Turn every fan off for the final state
       */
      useFans.setAllFans(fans, false, Fan.STOPPED);
      useFans.printHeading("Final state after turning every fan off");
      useFans.displayFans(fans);

      /*
       * Show that both display methods handle missing data
       */
      useFans.printHeading("Displaying an empty collection and a null fan");
      useFans.displayFans(new ArrayList<Fan>());
      useFans.displayFan(null);
    }
    catch(Exception e){

      useFans.logError("An unexpected error occurred", e);
    }
  }

  /*
   * Turns each text record into a Fan. A bad record is logged and
   * skipped, so one bad record does not stop the rest from loading.
   */
  public List<Fan> buildFans(String[] records){

    List<Fan> fans = new ArrayList<>();

    for(int i = 0; i < records.length; i++){

      try{

        fans.add(this.parseFan(records[i]));
        System.out.println("Record " + (i + 1) + " added: " + records[i]);
      }
      catch(NumberFormatException e){

        this.logError("Record " + (i + 1) + " (" + records[i]
            + ") has a value that is not a number", e);
      }
      catch(IllegalArgumentException e){

        this.logError("Record " + (i + 1) + " (" + records[i]
            + ") was rejected", e);
      }
    }

    System.out.println();
    return fans;
  }

  /*
   * Converts one record in the format speed,on,radius,color into a Fan
   */
  public Fan parseFan(String record){

    if(record == null){

      throw new IllegalArgumentException("The record is empty.");
    }

    String[] parts = record.split(",");

    if(parts.length != 4){

      throw new IllegalArgumentException("Expected 4 values but found "
          + parts.length + ".");
    }

    int speed = Integer.parseInt(parts[0].trim());
    boolean on = Boolean.parseBoolean(parts[1].trim());
    double radius = Double.parseDouble(parts[2].trim());
    String color = parts[3].trim();

    return new Fan(speed, on, radius, color);
  }

  /*
   * Displays every fan in the collection. It calls displayFan() for
   * each one, so the display format is only written in one place.
   */
  public void displayFans(List<Fan> fans){

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
   * Displays one fan using its getters. toString() is not used.
   */
  public void displayFan(Fan fan){

    if(fan == null){

      System.out.println("There is no fan to display.");
      System.out.println();
      return;
    }

    StringBuilder output = new StringBuilder();

    output.append("  On/Off : ").append(fan.isOn() ? "on" : "off").append("\n");
    output.append("  Speed  : ").append(fan.getSpeedName())
          .append(" (").append(fan.getSpeed()).append(")\n");
    output.append("  Radius : ").append(fan.getRadius()).append("\n");
    output.append("  Color  : ").append(fan.getColor()).append("\n");

    System.out.println(output);
  }

  /*
   * Turns every fan in the collection on or off and gives each one
   * the same speed
   */
  public void setAllFans(List<Fan> fans, boolean on, int speed){

    for(Fan fan : fans){

      fan.setOn(on);
      fan.setSpeed(speed);
    }
  }

  /*
   * Tries a bad speed, radius, and color on each fan. Each value is in
   * its own try block, so all three checks run for every fan.
   */
  public void tryInvalidValues(List<Fan> fans){

    for(int i = 0; i < fans.size(); i++){

      Fan fan = fans.get(i);
      String label = "Fan " + (i + 1);

      try{

        fan.setSpeed(5);
      }
      catch(IllegalArgumentException e){

        this.logError(label + " speed change was rejected", e);
      }

      try{

        fan.setRadius(-2);
      }
      catch(IllegalArgumentException e){

        this.logError(label + " radius change was rejected", e);
      }

      try{

        fan.setColor(" ");
      }
      catch(IllegalArgumentException e){

        this.logError(label + " color change was rejected", e);
      }

      System.out.println();
    }
  }

  /*
   * Writes a caught exception to the console in one consistent format
   */
  public void logError(String context, Exception e){

    System.out.println("  [ERROR] " + context + ": " + e.getMessage());
  }

  /*
   * Prints a section heading so the output is easy to follow
   */
  public void printHeading(String title){

    System.out.println("===== " + title + " =====");
    System.out.println();
  }
}

/*
 * Fan class from Module 6, kept in this file so UseFans compiles on its
 * own. It models a fan with a speed, an on/off switch, a radius, and a
 * color. Every value is checked in the setters, and the constructors use
 * the setters, so an invalid fan can never be created.
 */
class Fan{

  /*
   * Speed constants. These are static, so 'this' is not used with them.
   */
  public static final int STOPPED = 0;
  public static final int SLOW = 1;
  public static final int MEDIUM = 2;
  public static final int FAST = 3;

  /*
   * Speed names, indexed by the speed constants above
   */
  private static final String[] SPEED_NAMES = {"STOPPED", "SLOW", "MEDIUM", "FAST"};

  /*
   * Default values used by the no-argument constructor
   */
  private static final double DEFAULT_RADIUS = 6;
  private static final String DEFAULT_COLOR = "white";

  /*
   * Fields that describe the state of the fan
   */
  private int speed;
  private boolean on;
  private double radius;
  private String color;

  /*
   * No-argument constructor. this(...) calls the argument constructor,
   * so the default values are only set in one place.
   */
  public Fan(){

    this(STOPPED, false, DEFAULT_RADIUS, DEFAULT_COLOR);
  }

  /*
   * Argument constructor. It uses the setters, so every value goes
   * through the same validation.
   */
  public Fan(int speed, boolean on, double radius, String color){

    this.setSpeed(speed);
    this.setOn(on);
    this.setRadius(radius);
    this.setColor(color);
  }

  /*
   * Getters
   */
  public int getSpeed(){

    return this.speed;
  }

  public boolean isOn(){

    return this.on;
  }

  public double getRadius(){

    return this.radius;
  }

  public String getColor(){

    return this.color;
  }

  /*
   * Returns the name of the current speed, such as "MEDIUM"
   */
  public String getSpeedName(){

    return SPEED_NAMES[this.speed];
  }

  /*
   * Setters. The parameter names match the field names, so 'this'
   * tells Java which one is the field.
   */
  public void setSpeed(int speed){

    if(speed < STOPPED || speed > FAST){

      throw new IllegalArgumentException("Invalid speed: " + speed
          + ". Speed must be between " + STOPPED + " and " + FAST + ".");
    }

    this.speed = speed;
  }

  public void setOn(boolean on){

    this.on = on;
  }

  public void setRadius(double radius){

    if(radius <= 0){

      throw new IllegalArgumentException("Invalid radius: " + radius
          + ". Radius must be greater than 0.");
    }

    this.radius = radius;
  }

  public void setColor(String color){

    if(color == null || color.trim().isEmpty()){

      throw new IllegalArgumentException("Invalid color. Color cannot be empty.");
    }

    this.color = color.trim();
  }

  /*
   * Returns a description of the fan's current state. Kept from
   * Module 6 and used by TestFan. UseFans does not call this method.
   */
  @Override
  public String toString(){

    return "Fan is " + (this.on ? "on" : "off") + "\n" +
           "Speed = " + this.getSpeedName() + " (" + this.speed + ")\n" +
           "Radius = " + this.radius + "\n" +
           "Color = " + this.color;
  }
}

/*
 * TestFan, the Module 6 test program, kept in this file so all of the
 * Module 6 code is included. It creates one fan with the no-argument
 * constructor and one with the argument constructor, then uses the
 * getters, setters, and toString() to show how the Fan class works.
 * It also tries some invalid values to show the error handling.
 *
 * To run it after compiling: java TestFan
 */
class TestFan{

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
       * Try invalid values. Each one should be rejected, and the
       * fan should keep its old value.
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
   * Prints a heading and the fan's state using toString()
   */
  private static void printFan(String heading, Fan fan){

    System.out.println(heading + ":");
    System.out.println(fan);
    System.out.println();
  }

  /*
   * Helper methods that try a value and print the error if the
   * Fan class rejects it
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
