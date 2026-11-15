/*
 * Rakesh Shrestha
 * CSD 402 Java for Programmers
 * Module 10.2 Programming Assignment
 * November 15, 2026
 *
 * InternationalDivision is a concrete subclass of Division.
 * It adds the country where the division is located and the
 * language spoken there.
 *
 * References:
 * Liang, Y. D. (2024). Introduction to Java programming and data
 *   structures (13th ed.). Pearson. Chapter 13.
 * W3Schools. (n.d.). Java abstraction.
 *   https://www.w3schools.com/java/java_abstract.asp
 */
public class InternationalDivision extends Division {

  private final String country;
  private final String language;

  /*
   * All four fields are required. The first two go up to Division.
   */
  public InternationalDivision(String divisionName, int accountNumber,
      String country, String language) {

    super(divisionName, accountNumber);
    this.country = requireText(country, "Country");
    this.language = requireText(language, "Language");
  }

  public String getCountry() {
    return country;
  }

  public String getLanguage() {
    return language;
  }

  @Override
  public void display() {

    System.out.println("International Division");
    displayCommon();
    System.out.println("Country:        " + country);
    System.out.println("Language:       " + language);
  }
}
