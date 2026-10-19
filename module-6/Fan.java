/*
 * Rakesh Shrestha
 * Bellevue University
 * CSD-402 Java for Programmers
 * Module 6.2 Programming Assignment
 * October 18, 2026
 *
 * Fan class that models a fan with a speed, an on/off switch,
 * a radius, and a color.
 *
 * Attribution: Based on the Fan class exercise in Liang, Introduction
 * to Java Programming and Data Structures, Chapter 9 (Objects and
 * Classes), and the Module 6 class examples by Professor Darrell Payne.
 */
public class Fan{

  /*
   * Speed constants
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
   * No-argument constructor. Calls the argument constructor with the
   * default values so the defaults are only set in one place.
   */
  public Fan(){

    this(STOPPED, false, DEFAULT_RADIUS, DEFAULT_COLOR);
  }

  /*
   * Argument constructor. Uses the setters so every value goes
   * through the same validation checks.
   */
  public Fan(int speed, boolean on, double radius, String color){

    setSpeed(speed);
    setOn(on);
    setRadius(radius);
    setColor(color);
  }

  /*
   * Getters
   */
  public int getSpeed(){

    return speed;
  }

  public boolean isOn(){

    return on;
  }

  public double getRadius(){

    return radius;
  }

  public String getColor(){

    return color;
  }

  /*
   * Setters
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
   * Returns the name of the current speed, such as "MEDIUM",
   * so toString() can show a readable value.
   */
  private String getSpeedName(){

    switch(speed){

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
   * Returns a description of the fan's current state
   */
  @Override
  public String toString(){

    return "Fan is " + (on ? "on" : "off") + "\n" +
           "Speed = " + getSpeedName() + " (" + speed + ")\n" +
           "Radius = " + radius + "\n" +
           "Color = " + color;
  }
}
