import java.util.Scanner;

/**
 * WaterHeatingCalculator.java
 *
 * Course: CSD-402 Java for Programmers
 * Assignment: Module 1.3 Programming Assignment
 * Author: Rakesh Shrestha
 * Date: September 13, 2026
 *
 * Description:
 * This program calculates the amount of energy, in Joules, required to
 * heat a given mass of water from an initial temperature to a final
 * temperature. The user is prompted to enter the mass of the water in
 * kilograms along with the initial and final temperatures in Celsius.
 * The program then applies the formula below and displays the result.
 *
 *     Q = waterMass * (finalTemperature - initialTemperature) * 4184
 *
 * Where:
 *     waterMass           - mass of the water in kilograms
 *     finalTemperature    - final temperature of the water in Celsius
 *     initialTemperature  - initial temperature of the water in Celsius
 *     Q                   - energy needed, in Joules
 */
public class WaterHeatingCalculator {

    public static void main(String[] args) {

        // Scanner object used to read user input from the console
        Scanner input = new Scanner(System.in);

        // Variables to hold the water mass and the two temperatures
        double waterMass;
        double initialTemperature;
        double finalTemperature;

        // Prompt the user for the mass of the water in kilograms
        System.out.print("Enter the amount of water in kilograms: ");
        waterMass = input.nextDouble();

        // Prompt the user for the initial temperature of the water
        System.out.print("Enter the initial temperature of the water (Celsius): ");
        initialTemperature = input.nextDouble();

        // Prompt the user for the final temperature of the water
        System.out.print("Enter the final temperature of the water (Celsius): ");
        finalTemperature = input.nextDouble();

        // Calculate the energy needed to heat the water using the given formula
        double energyNeeded = waterMass * (finalTemperature - initialTemperature) * 4184;

        // Display the result to the user, formatted to two decimal places
        System.out.printf("The energy needed to heat the water is %.2f Joules.%n", energyNeeded);

        // Close the Scanner to release the input resource
        input.close();
    }
}
