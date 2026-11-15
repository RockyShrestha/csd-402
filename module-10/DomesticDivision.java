/*
 * Rakesh Shrestha
 * CSD 402 Java for Programmers
 * Module 10.2 Programming Assignment
 * November 15, 2026
 *
 * DomesticDivision is a concrete subclass of Division.
 * It adds the state where the division is located.
 *
 * References:
 * Liang, Y. D. (2024). Introduction to Java programming and data
 *   structures (13th ed.). Pearson. Chapter 13.
 * W3Schools. (n.d.). Java abstraction.
 *   https://www.w3schools.com/java/java_abstract.asp
 */
public class DomesticDivision extends Division {

  private final String state;

  /*
   * All three fields are required. The first two go up to Division.
   */
  public DomesticDivision(String divisionName, int accountNumber,
      String state) {

    super(divisionName, accountNumber);
    this.state = requireText(state, "State");
  }

  public String getState() {
    return state;
  }

  @Override
  public void display() {

    System.out.println("Domestic Division");
    displayCommon();
    System.out.println("State:          " + state);
  }
}
