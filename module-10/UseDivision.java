/*
 * Rakesh Shrestha
 * CSD 402 Java for Programmers
 * Module 10.2 Programming Assignment
 * November 15, 2026
 *
 * UseDivision tests the Division classes. It creates two
 * InternationalDivision objects and two DomesticDivision objects,
 * stores them as Division references, and calls display() on each one.
 *
 * References:
 * Liang, Y. D. (2024). Introduction to Java programming and data
 *   structures (13th ed.). Pearson. Chapter 13.
 * W3Schools. (n.d.). Java abstraction.
 *   https://www.w3schools.com/java/java_abstract.asp
 * Payne, D. (2020). Example_1_01.java [Course example]. Bellevue University.
 */
public class UseDivision {

  public static void main(String[] args) {

    // Error, an abstract class cannot be instantiated
    // Division division = new Division("Sales", 1001);

    try {

      // The array type is the abstract class, the objects are concrete
      Division[] divisions = {
        new InternationalDivision("European Sales", 2001, "Germany", "German"),
        new InternationalDivision("Asia Pacific Support", 2002, "Japan",
            "Japanese"),
        new DomesticDivision("Midwest Operations", 1001, "Nebraska"),
        new DomesticDivision("Southern Distribution", 1002, "Texas")
      };

      System.out.println();
      System.out.println("Company Divisions");
      System.out.println("-----------------------------------");

      // Java picks the right display() for each object at runtime
      for (Division division : divisions) {

        division.display();
        System.out.println("-----------------------------------");
      }

    } catch (IllegalArgumentException e) {

      // Thrown by the constructors when a field is missing or invalid
      System.out.println("Could not create division: " + e.getMessage());
    }
  }
}
