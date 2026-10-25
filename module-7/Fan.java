/*
 * Rakesh Shrestha
 * Bellevue University
 * CSD-402 Java for Programmers
 * Module 7.2 Programming Assignment
 * October 25, 2026
 *
 * Fan class that models a fan with a speed, an on/off switch,
 * a radius, and a color.
 *
 * Module 7 changes: The 'this' reference is now used throughout the
 * class wherever Java allows it (fields, method calls inside the
 * class, and constructor chaining). It cannot be used inside static
 * members such as the constants. getSpeedName() was also made public
 * so UseFans can show the speed name without calling toString().
 *
 * Attribution: Based on the Fan class exercise in Liang, Introduction
 * to Java Programming and Data Structures, Chapter 9 (Objects and
 * Classes), and the Module 6 and Module 7 class examples by Professor
 * Darrell Payne.
 */
public class Fan{

  /*
   * Speed constants. These are static, so 'this' is not used
   * with them.
   */
  public static final int STOPPED = 0;
  public static final int SLOW = 1;
  public static final int MEDIUM = 2;
  public static final int FAST = 3;

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
   * No-argument constructor. this(...) calls the argument
   * constructor with the default values so the defaults are
   * only set in one place.
   */
  public Fan(){

    this(STOPPED, false, DEFAULT_RADIUS, DEFAULT_COLOR);
  }

  /*
   * Argument constructor. Uses the setters so every value goes
   * through the same validation checks.
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
   * Setters. The parameter names match the field names, so
   * 'this' tells Java which one is the field.
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
   * Returns the name of the current speed, such as "MEDIUM".
   * Public now so other classes can use it for display.
   */
  public String getSpeedName(){

    switch(this.speed){

      case SLOW:
        return "SLOW";
      case MEDIUM:
        return "MEDIUM";
      case FAST:
        return "FAST";
      default:
        return "STOPPED";
    }
  }

  /*
   * Returns a description of the fan's current state.
   * Kept from Module 6. UseFans does not call this method.
   */
  @Override
  public String toString(){

    return "Fan is " + (this.on ? "on" : "off") + "\n" +
           "Speed = " + this.getSpeedName() + " (" + this.speed + ")\n" +
           "Radius = " + this.radius + "\n" +
           "Color = " + this.color;
  }
}
