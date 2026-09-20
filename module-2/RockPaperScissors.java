import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Course: CSD-402 - Java for Programmers
 * Module: 2.2 Programming Assignment
 * Author: Rakesh Shrestha
 * Date: September 20, 2026
 *
 * Purpose: This program plays a single round of Rock-Paper-Scissors
 * against the computer. The computer's choice is generated randomly
 * using Math.random(), the user is prompted to enter a choice from
 * the keyboard, and the program then prints both choices along with
 * a clear statement of who won the round.
 */
public class RockPaperScissors {

    // Named-constants make the choice values self-explanatory
    // instead of relying on the reader to remember what 1, 2, and 3 mean.
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Generate the computer's choice: 1 = Rock, 2 = Paper, 3 = Scissors
        int computerChoice = (int) (Math.random() * 3) + 1;

        // Prompt the user until a valid choice (1, 2, or 3) is entered.
        // A try-catch guards against non-numeric input (e.g., letters),
        // which would otherwise throw an uncaught InputMismatchException.
        int userChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter a value of 1 for rock, 2 for paper, or 3 for scissors: ");
            try {
                userChoice = input.nextInt();
                if (userChoice >= ROCK && userChoice <= SCISSORS) {
                    validInput = true;
                } else {
                    System.out.println("Invalid entry. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a whole number (1, 2, or 3).");
                input.next(); // discard the non-numeric token so the loop doesn't spin forever
            }
        }

        // Convert each numeric choice into its matching word
        String computerName = nameForChoice(computerChoice);
        String userName = nameForChoice(userChoice);

        // Display both selections
        System.out.println("\nThe computer chose " + computerName + ".");
        System.out.println("You chose " + userName + ".");

        // Determine and display the outcome
        if (computerChoice == userChoice) {
            System.out.println("It's a tie! You both chose " + userName + ".");
        } else if ((userChoice == ROCK && computerChoice == SCISSORS)
                || (userChoice == PAPER && computerChoice == ROCK)
                || (userChoice == SCISSORS && computerChoice == PAPER)) {
            System.out.println(userName + " beats " + computerName + ". You win!");
        } else {
            System.out.println(computerName + " beats " + userName + ". The computer wins!");
        }

        input.close();
    }

    /**
     * Converts a numeric choice (1, 2, or 3) into its matching name.
     *
     * @param choice the numeric choice, expected to be 1, 2, or 3
     * @return "Rock" for 1, "Paper" for 2, or "Scissors" for 3
     */
    public static String nameForChoice(int choice) {
        String name;

        switch (choice) {
            case ROCK:
                name = "Rock";
                break;
            case PAPER:
                name = "Paper";
                break;
            case SCISSORS:
                name = "Scissors";
                break;
            default:
                name = "Unknown";
        }

        return name;
    }
}
