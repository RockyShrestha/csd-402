/*
 * Rakesh Shrestha
 * CSD 402 Java for Programmers
 * Module 10.2 Programming Assignment
 * November 15, 2026
 *
 * Division is the abstract superclass for the company divisions.
 * It holds the two fields every division has (name and account number)
 * and leaves display() abstract so each subclass prints its own details.
 *
 * References:
 * Liang, Y. D. (2024). Introduction to Java programming and data
 *   structures (13th ed.). Pearson. Chapter 13.
 * W3Schools. (n.d.). Java abstraction.
 *   https://www.w3schools.com/java/java_abstract.asp
 * Payne, D. (2020). Example_1_01.java [Course example]. Bellevue University.
 */
public abstract class Division {

  private final String divisionName;
  private final int accountNumber;

  /*
   * Both fields are required. The subclasses call this with super().
   * Bad values are rejected here so the check lives in one place.
   */
  public Division(String divisionName, int accountNumber) {

    if (accountNumber <= 0) {
      throw new IllegalArgumentException(
          "Account number must be a positive number.");
    }

    this.divisionName = requireText(divisionName, "Division name");
    this.accountNumber = accountNumber;
  }

  public String getDivisionName() {
    return divisionName;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  /*
   * Each subclass decides how its information is shown.
   */
  public abstract void display();

  /*
   * Prints the fields shared by every division.
   * Subclasses call this first inside display() and then add their own lines.
   */
  protected void displayCommon() {

    System.out.println("Division name:  " + divisionName);
    System.out.println("Account number: " + accountNumber);
  }

  /*
   * Makes sure a text field is not null or blank.
   * Returns the trimmed value so it can be assigned right away.
   */
  protected static String requireText(String value, String fieldName) {

    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be empty.");
    }

    return value.trim();
  }
}
